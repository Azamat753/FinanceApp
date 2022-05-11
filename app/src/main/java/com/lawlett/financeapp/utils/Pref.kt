package com.lawlett.financeapp.utils

import android.content.SharedPreferences
import javax.inject.Inject

class Pref @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun isShowBalance(): Boolean = sharedPreferences.getBoolean(BALANCE, false)

    fun showBalance() = sharedPreferences.edit().putBoolean(BALANCE, true).apply()

    fun isShowHistory(): Boolean = sharedPreferences.getBoolean(HISTORY, false)

    fun showHistory() = sharedPreferences.edit().putBoolean(HISTORY, true).apply()

    fun isShowPlan(): Boolean = sharedPreferences.getBoolean(PLAN, false)

    fun showPlan() = sharedPreferences.edit().putBoolean(PLAN, true).apply()

    fun isShowCardPlan(): Boolean = sharedPreferences.getBoolean(CARD_PLAN, false)

    fun showCardPlan() = sharedPreferences.edit().putBoolean(CARD_PLAN, true).apply()
}