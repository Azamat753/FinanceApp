package com.lawlett.financeapp.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lawlett.financeapp.R
import com.lawlett.financeapp.databinding.FragmentBalanceBinding
import com.lawlett.financeapp.sheetdialog.ChangeBalanceSheetDialogFragment
import com.redmadrobot.extensions.viewbinding.viewBinding


class BalanceFragment : Fragment(R.layout.fragment_balance) {
    private val binding: FragmentBalanceBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            incomeLayout.setOnClickListener {
                ChangeBalanceSheetDialogFragment().show(requireActivity().supportFragmentManager,getString(R.string.income))
            }
            costLayout.setOnClickListener {
                ChangeBalanceSheetDialogFragment().show(requireActivity().supportFragmentManager,getString(R.string.cost))
            }
        }
    }

}