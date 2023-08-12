package com.luukitoo.animapp.extension

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openUrl(url: String?) {
    url?.let {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}

fun Context.shareText(text: String?) {
    text?.let {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(this, null))
        }
    }
}