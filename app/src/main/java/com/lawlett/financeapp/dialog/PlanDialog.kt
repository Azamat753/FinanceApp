package com.lawlett.financeapp.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import com.lawlett.financeapp.R
import com.lawlett.financeapp.databinding.DialogPlanBinding
import java.util.*
import kotlin.collections.ArrayList

class PlanDialog(
    private val context: Context,
    private val activity: Activity,
    private val result: Result
) {
    private var day = ""

    private var month = ""

    private var date = ""

    private var dayCalendar = 0

    private var monthCalendar = 0

    private var yearCalendar = 0

    private lateinit var datePicker: DatePickerDialog


    private lateinit var calendar: Calendar

    private lateinit var listenerForDate: DatePickerDialog.OnDateSetListener

    private val monthNames: ArrayList<String> by lazy {
        arrayListOf(
            context.getString(R.string.january),
            context.getString(R.string.february),
            context.getString(R.string.march),
            context.getString(R.string.april),
            context.getString(R.string.may),
            context.getString(R.string.june),
            context.getString(R.string.July),
            context.getString(R.string.August),
            context.getString(R.string.september),
            context.getString(R.string.October),
            context.getString(R.string.november),
            context.getString(R.string.December)
        )
    }

    private lateinit var dialog: Dialog

    private lateinit var binding: DialogPlanBinding

    init {
        createDialog()
    }

    private fun createDialog() {
        val builder = AlertDialog.Builder(activity)
        val view = activity.layoutInflater.inflate(R.layout.dialog_plan, null)
        builder.setView(view)
        dialog = builder.create()
        binding = DialogPlanBinding.bind(view)
    }


    fun initDialog(isMonth: Boolean, id: Int) {
        dialog.cancel()
        createDialog()
        initDate()
        initCalendar()
        initListener()
        initPicker()
        initMin(isMonth)
        initMax(isMonth)
        initBtn(id)
        show()

    }

    private fun initBtn(id: Int) {
        binding.btnChooseMonth.setOnClickListener {
            datePicker.show()
        }
        binding.btnOk.setOnClickListener {
            val source = binding.editTitle.text.toString()
            val amount = binding.editSum.text.toString()
            result.transaction(date, month, amount, source, day, id)
        }
    }

    private fun initListener() {
        listenerForDate = DatePickerDialog.OnDateSetListener { _, _, month, day ->
            this.month = monthNames[month]
            this.day = day.toString()
            date = "$day ${this.month}"
            binding.btnChooseMonth.text = date
        }
    }

    private fun initPicker() {
        datePicker = DatePickerDialog(
            context, R.style.DialogTheme, listenerForDate,
            yearCalendar, monthCalendar, dayCalendar
        )
    }

    private fun initCalendar() {
        dayCalendar = calendar.get(Calendar.DAY_OF_MONTH)
        monthCalendar = calendar.get(Calendar.MONTH)
        yearCalendar = calendar.get(Calendar.YEAR)
    }

    private fun initDate() {
        calendar = Calendar.getInstance()
    }

    private fun initMax(isMonth: Boolean) {
        if (isMonth) {
            calendar.add(Calendar.MONTH, +1)
            calendar.set(Calendar.DAY_OF_MONTH, 0)
        } else {
            calendar.add(Calendar.MONTH, +2)
            calendar.set(Calendar.DAY_OF_MONTH, 0)
        }
        datePicker.datePicker.maxDate = calendar.timeInMillis
    }

    private fun initMin(isMonth: Boolean) {
        val c = Calendar.getInstance()
        if (isMonth) {
            c.add(Calendar.MONTH, 0)
            c.set(Calendar.DAY_OF_MONTH, 1)
        } else {
            c.add(Calendar.MONTH, 1)
            c.set(Calendar.DAY_OF_MONTH, 1)
        }
        datePicker.datePicker.minDate = c.timeInMillis
    }

    private fun show() = dialog.show()

    fun dismiss() = dialog.dismiss()

    interface Result {
        fun transaction(
            date: String,
            month: String,
            amount: String,
            source: String,
            day: String,
            id: Int
        )
    }
}