package com.luukitoo.core.util

sealed class ResultStatus<T>(
    private val data: T? = null,
    private val throwable: Throwable? = null,
) {

    data class Success<T>(val data: T) : ResultStatus<T>(
        data = data,
    )

    data class Failure<T>(val throwable: Throwable) : ResultStatus<T>(
        throwable = throwable,
    )

    fun onSuccess(action: (T) -> Unit) {
        if (this is Success) {
            action.invoke(data)
        }
    }

    fun onFailure(action: (Throwable) -> Unit) {
        if (this is Failure) {
            action.invoke(throwable)
        }
    }

    inline fun <R> mapData(mapper: (T) -> R): ResultStatus<R> {
        return when (this) {
            is Success -> Success(mapper.invoke(data))
            is Failure -> Failure(throwable)
        }
    }
}