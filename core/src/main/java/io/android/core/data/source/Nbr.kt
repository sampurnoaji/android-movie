package io.android.core.data.source

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import io.android.core.vo.Either
import kotlinx.coroutines.flow.flow

@Suppress("LeakingThis")
abstract class Nbr<ResultType, RequestType> {

    fun asFlow() = flow {
        val dbSource = loadFromDb()
        if (shouldFetch(dbSource)) {
            when (val result = createCall()) {
                is Either.Success -> {
                    saveCallResult(result.data)
                    emit(loadFromDb())
                }
                is Either.Failure -> onFetchFailed(result.cause)
            }
        } else {
            emit(loadFromDb())
        }
    }

    @WorkerThread
    protected abstract suspend fun saveCallResult(response: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @WorkerThread
    protected abstract suspend fun loadFromDb(): ResultType

    @WorkerThread
    protected abstract suspend fun createCall(): Either<Exception, RequestType>

    protected open fun onFetchFailed(cause: Exception) {}
}