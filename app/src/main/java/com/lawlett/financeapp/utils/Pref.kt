package com.lawlett.financeapp.utils

import android.content.SharedPreferences
import javax.inject.Inject

class Pref @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun isShowBalance(): Boolean = sharedPreferences.getBoolean(BALANCE, false)

    fun showBalance() = sharedPreferences.edit().putBoolean(BALANCE, true).apply()

    fun isShowIntro(): Boolean = sharedPreferences.getBoolean(INTRO, false)

    fun showIntro() = sharedPreferences.edit().putBoolean(INTRO, true).apply()
}