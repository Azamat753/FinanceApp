package com.lawlett.financeapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.lawlett.domain.model.BalanceModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.databinding.FragmentBalanceBinding
import com.lawlett.financeapp.presenter.BalancePresenter
import com.lawlett.financeapp.presenter.ChangeBalancePresenter
import com.lawlett.financeapp.sheetdialog.ChangeBalanceSheetDialogFragment
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BalanceFragment : Fragment(R.layout.fragment_balance) {
    private val binding: FragmentBalanceBinding by viewBinding()

    @Inject
    lateinit var hiltPresenter: BalancePresenter
    var model = BalanceModel()

    override
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
        model = hiltPresenter.getIncome()
        Log.e("ololo", "onViewCreated: ${model}" )
    }

    private fun initClickers() {
        with(binding) {
            incomeLayout.setOnClickListener {
                ChangeBalanceSheetDialogFragment().show(
                    requireActivity().supportFragmentManager,
                    getString(R.string.income)
                )
            }
            costLayout.setOnClickListener {
                ChangeBalanceSheetDialogFragment().show(
                    requireActivity().supportFragmentManager,
                    getString(R.string.cost)
                )
            }
        }
    }
}