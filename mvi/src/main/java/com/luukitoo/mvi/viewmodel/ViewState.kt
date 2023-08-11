package com.luukitoo.mvi.viewmodel

import com.luukitoo.core.util.Disposable

abstract class ViewState {
    abstract val isLoading: Boolean
    abstract val throwable: Disposable<Throwable>?
}