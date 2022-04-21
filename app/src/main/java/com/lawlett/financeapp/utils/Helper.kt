package com.lawlett.financeapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible


fun View.gone() {
    this.isVisible = false
}

fun View.visible() {
    this.isVisible = true
}

const val UTC = "UTC"

fun Context.toastShow(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}