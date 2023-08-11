package com.luukitoo.mvi.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun ViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend (CoroutineScope) -> Unit,
): Job {
    return viewModelScope.launch(context) {
        block.invoke(this)
    }
}

fun <T> ViewModel.async(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend (CoroutineScope) -> T,
): Deferred<T> {
    return viewModelScope.async(context) {
        block.invoke(this)
    }
}

val ViewModel.thisScope get() = viewModelScope