package com.luukitoo.animapp.presentation.navigation

import com.luukitoo.core.extension.isNull

data class RouteArg(
    val key: String,
    val value: Any? = null,
) {

    override fun toString(): String {
        return if (value.isNull()) "{$key}" else value.toString()
    }
}
