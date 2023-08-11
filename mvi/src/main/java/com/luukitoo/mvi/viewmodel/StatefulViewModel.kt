package com.luukitoo.mvi.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StatefulViewModel<ScreenState: ViewState, ViewEvent : Any>(
    initialState: ScreenState
) : ViewModel() {

    private val _viewState = MutableStateFlow(initialState)
    val viewState = _viewState.asStateFlow()

    abstract fun onEvent(event: ViewEvent)

    protected fun updateState(stateBuilder: ScreenState.() -> ScreenState) {
        _viewState.value = stateBuilder.invoke(_viewState.value)
    }
}