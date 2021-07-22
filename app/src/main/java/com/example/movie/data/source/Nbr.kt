package com.example.movie.data.source

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.movie.utils.Either
import kotlinx.coroutines.flow.flow

// ResultType: Type for the Resource data.
// RequestType: Type for the API response.
@Suppress("LeakingThis")
abstract class Nbr<ResultType, RequestType> {

    fun asFlow() = flow {
        val dbSource = loadFromDb()
        if (shouldFetch(dbSource)) {
            when (val result = createCall()) {
                is Either.Success -> saveCallResult(result.data)
                is Either.Failure -> onFetchFailed(result.cause)
            }
        } else {
            emit(loadFromDb())
        }
    }

    // Called to save the result of the API response into the database
    @WorkerThread
    protected abstract suspend fun saveCallResult(response: RequestType)

    // Called with the data in the database to decide whether to fetch
    // potentially updated data from the network.
    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    // Called to get the cached data from the database.
    @WorkerThread
    protected abstract suspend fun loadFromDb(): ResultType

    // Called to create the API call.
    @WorkerThread
    protected abstract suspend fun createCall(): Either<Exception, RequestType>

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    protected open fun onFetchFailed(cause: Exception) {}
}