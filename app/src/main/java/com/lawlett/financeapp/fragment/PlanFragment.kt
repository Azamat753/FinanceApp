package com.lawlett.financeapp.fragment

import androidx.fragment.app.Fragment
import com.lawlett.financeapp.R
import com.lawlett.financeapp.databinding.FragmentPlanBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanFragment : Fragment(R.layout.fragment_plan) {
    private val binding: FragmentPlanBinding by viewBinding()
}