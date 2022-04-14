package com.lawlett.financeapp.utils

import android.view.View
import androidx.core.view.isVisible


fun checkIcon(number: Int): Boolean = number != 0


fun checkNumber(number: Int): Boolean = number >= 0

fun checkNumberToZero(number: Int): Boolean = number != 0

fun View.gone() {
    this.isVisible = false
}

fun View.visible() {
    this.isVisible = true
}