package com.lawlett.financeapp.sheetdialog

import android.app.DatePickerDialog
import android.widget.Toast
import com.example.core.base.BaseBottomSheetDialog
import com.lawlett.financeapp.R
import com.lawlett.financeapp.databinding.FragmentAddMonthSheetDialogBinding
import com.lawlett.financeapp.presenter.AddMonthPresenter
import com.lawlett.financeapp.utils.UTC
import com.lawlett.view.AddMonthView
import dagger.hilt.android.AndroidEntryPoint
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddMonthSheetDialog(private val result: Result) :
    BaseBottomSheetDialog<FragmentAddMonthSheetDialogBinding>(
        FragmentAddMonthSheetDialogBinding::inflate
    ), AddMonthView {
    private var day = ""

    private var month = ""

    private var date = ""

    private var dayCalendar = 0

    private var monthCalendar = 0

    private var yearCalendar = 0

    private var isMonth = false

    private lateinit var datePicker: DatePickerDialog


    private lateinit var calendar: Calendar

    private lateinit var listenerForDate: DatePickerDialog.OnDateSetListener

    private val monthNames: ArrayList<String> by lazy {
        arrayListOf(
            getString(R.string.january),
            getString(R.string.february),
            getString(R.string.march),
            getString(R.string.april),
            getString(R.string.may),
            getString(R.string.june),
            getString(R.string.July),
            getString(R.string.August),
            getString(R.string.september),
            getString(R.string.October),
            getString(R.string.november),
            getString(R.string.December)
        )
    }

    @Inject
    lateinit var hiltPresenter: AddMonthPresenter

    @InjectPresenter
    lateinit var presenter: AddMonthPresenter

    @ProvidePresenter
    fun providePresenter(): AddMonthPresenter {
        return hiltPresenter
    }


    override fun initClickers() {
        initDate()
        initCalendar()
        initListener()
        initPicker()
        initMin()
        initMax()
        initBtn()
    }


    private fun initMax() {
        calendar.add(Calendar.MONTH, +2)
        calendar.set(Calendar.DAY_OF_MONTH, 0)
        datePicker.datePicker.maxDate = calendar.timeInMillis
    }

    private fun initMin() {
        val c = Calendar.getInstance(TimeZone.getTimeZone(UTC))
        c.add(Calendar.MONTH, 0)
        c.set(Calendar.DAY_OF_MONTH, 1)
        datePicker.datePicker.minDate = c.timeInMillis
    }

    private fun initPicker() {
        datePicker = DatePickerDialog(
            requireContext(), R.style.DialogTheme, listenerForDate,
            yearCalendar, monthCalendar, dayCalendar
        )
    }

    private fun initListener() {
        listenerForDate = DatePickerDialog.OnDateSetListener { _, _, month, day ->
            this.month = monthNames[month]
            this.day = day.toString()
            checkMonth(month)
            date = "$day ${this.month}"
            binding.btnDate.text = date
        }
    }

    private fun checkMonth(month: Int) {
        isMonth = month == monthCalendar
    }

    private fun initCalendar() {
        dayCalendar = calendar.get(Calendar.DAY_OF_MONTH)
        monthCalendar = calendar.get(Calendar.MONTH)
        yearCalendar = calendar.get(Calendar.YEAR)
    }

    private fun initBtn() {
        binding.btnDate.setOnClickListener {
            datePicker.show()
        }
        binding.btnAccept.setOnClickListener {
            createMonth(date, month, day, binding.editExpectedSum.text.toString())

        }
    }

    private fun createMonth(date: String, month: String, day: String, amount: String) =
        presenter.createPlan(date, month, amount, day, isMonth)


    private fun initDate() {
        calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC))
    }

    override fun closeDialog() {
        dismiss()
        result.updateDate()
    }

    override fun notZero() {
        toast(getString(R.string.number_not_zero))
    }

    private fun toast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    override fun notNegative() {
        toast(getString(R.string.not_negative_number))
    }

    override fun notEmptyAmount() {
        toast(getString(R.string.enter_amount))
    }

    override fun notEmptyDate() {
        toast(getString(R.string.specify_date))
    }

    interface Result {
        fun updateDate()
    }

}