package com.lawlett.financeapp.fragment

import com.lawlett.financeapp.R
import com.lawlett.financeapp.databinding.FragmentPlanBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment

@AndroidEntryPoint
class PlanFragment : MvpAppCompatFragment(R.layout.fragment_plan) {
    private val binding: FragmentPlanBinding by viewBinding()
}
