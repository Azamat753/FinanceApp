package com.lawlett.financeapp.utils

import android.content.Context
import android.widget.Toast

fun checkNumber(number: Int): Boolean = number != 0

fun toast(context: Context, message: String) =
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()