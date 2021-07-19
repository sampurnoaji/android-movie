package com.example.movie.data.util

import com.example.movie.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ApiClient {
    open suspend fun <T> call(api: suspend () -> Response<T>): ApiResponse<Exception, T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api()
                when {
                    response.isSuccessful && response.body() != null -> ApiResponse.Success(response.body()!!)
                    response.code() in 400..499 -> ApiResponse.Failure(ClientErrorException(response.code()))
                    response.code() in 500..599 -> ApiResponse.Failure(ServerErrorException(response.code()))
                    else -> ApiResponse.Failure(UnknownNetworkErrorException(response.errorBody().toString()))
                }
            } catch (exception: Exception) {
                handleError(exception)
            }
        }
    }

    private fun <T> handleError(exception: Exception): ApiResponse.Failure<Exception, T> {
        return when (exception) {
            is UnknownHostException -> ApiResponse.Failure(NoInternetConnection())
            is SocketTimeoutException -> ApiResponse.Failure(TimeoutException())
            is IOException -> ApiResponse.Failure(UnknownNetworkErrorException(exception.message ?: "Unknown IO Exception error"))
            else -> ApiResponse.Failure(UnknownNetworkErrorException(exception.localizedMessage ?: "Unknown error"))
        }
    }
}