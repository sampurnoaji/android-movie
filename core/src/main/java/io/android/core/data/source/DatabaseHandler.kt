package io.android.core.data.source

import io.android.core.vo.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class DatabaseHandler {
    open suspend fun <T> load(dao: suspend () -> T): DataResponse<Exception, T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = dao()
                DataResponse.Success(response)
            } catch (exception: Exception) {
                DataResponse.Failure(exception)
            }
        }
    }
}