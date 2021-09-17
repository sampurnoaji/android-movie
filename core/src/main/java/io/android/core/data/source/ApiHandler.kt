package io.android.core.data.source

import io.android.core.util.ClientErrorException
import io.android.core.util.NoInternetConnection
import io.android.core.util.ServerErrorException
import io.android.core.util.TimeoutException
import io.android.core.util.UnknownNetworkErrorException
import io.android.core.vo.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ApiHandler {
    open suspend fun <T> call(api: suspend () -> Response<T>): DataResponse<Exception, T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api()
                when {
                    response.isSuccessful && response.body() != null -> DataResponse.Success(response.body()!!)
                    response.code() in 400..499 -> DataResponse.Failure(ClientErrorException(response.code()))
                    response.code() in 500..599 -> DataResponse.Failure(ServerErrorException(response.code()))
                    else -> DataResponse.Failure(UnknownNetworkErrorException(response.errorBody().toString()))
                }
            } catch (exception: Exception) {
                handleError(exception)
            }
        }
    }

    private fun <T> handleError(exception: Exception): DataResponse.Failure<Exception, T> {
        return when (exception) {
            is UnknownHostException -> DataResponse.Failure(NoInternetConnection())
            is SocketTimeoutException -> DataResponse.Failure(TimeoutException())
            is IOException -> DataResponse.Failure(UnknownNetworkErrorException(exception.message ?: "Unknown IO Exception error"))
            else -> DataResponse.Failure(UnknownNetworkErrorException(exception.localizedMessage ?: "Unknown error"))
        }
    }
}