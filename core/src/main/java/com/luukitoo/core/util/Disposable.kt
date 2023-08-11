package com.luukitoo.core.util

data class Disposable<T>(private var value: T?) {

    fun get(): T? = value.also {
        value = null
    }
}