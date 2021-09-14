package io.android.core.util

data class ClientErrorException(val code: Int) : Exception()
data class ServerErrorException(val code: Int) : Exception()
data class UnknownNetworkErrorException(val errorMessage: String) : Exception()
class EmptyResponseException : Exception()
class NoInternetConnection : Exception()
class TimeoutException : Exception()
