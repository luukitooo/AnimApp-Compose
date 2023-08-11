package com.luukitoo.core.util

import com.luukitoo.core.extension.notNull
import retrofit2.Response

object NetworkCaller {

    suspend fun <T> safeCall(call: suspend () -> Response<T>): ResultStatus<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful && response.body().notNull()) {
                return ResultStatus.Success(response.body()!!)
            }
            return ResultStatus.Failure(Exception(response.errorBody()?.string()))
        } catch (e: Exception) {
            return ResultStatus.Failure(e)
        }
    }
}