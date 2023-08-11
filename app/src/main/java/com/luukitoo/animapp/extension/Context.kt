package com.luukitoo.animapp.extension

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openUrl(url: String?) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

fun Context.shareText(text: String?) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, text)
    startActivity(Intent.createChooser(intent, "Share Text"))
}