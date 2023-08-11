package com.luukitoo.animapp.presentation.navigation

import com.luukitoo.core.extension.isNull

data class RouteArg(
    val key: String,
    val value: Any? = null,
) {
    fun process() = if (value.isNull()) "{$key}" else value
}
