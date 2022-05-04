package com.lawlett.financeapp.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import com.lawlett.financeapp.R
import com.lawlett.financeapp.databinding.DialogWarningBinding

class DialogWarning(private val activity: Activity, private val result: Result) {
    private lateinit var dialog: Dialog

    private lateinit var binding: DialogWarningBinding

    init {
        createDialog()
    }

    private fun createDialog() {
        val builder = AlertDialog.Builder(activity)
        val view = activity.layoutInflater.inflate(R.layout.dialog_warning, null)
        builder.setView(view)
        dialog = builder.create()
        binding = DialogWarningBinding.bind(view)
    }

    fun open(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String
    ) {
        show()
        initBtn(amount, icon, iconName, date, month)
    }

    private fun initBtn(amount: String, icon: Int, iconName: String, date: String, month: String) {
        binding.btnConfirm.setOnClickListener {
            result.confirm(amount, icon, iconName, date, month)
            dismiss()
        }
        binding.btnCancel.setOnClickListener { dismiss() }
    }

    fun show() = dialog.show()

    private fun dismiss() = dialog.dismiss()

    interface Result {
        fun confirm(
            amount: String,
            icon: Int,
            iconName: String,
            date: String,
            month: String
        )
    }
}