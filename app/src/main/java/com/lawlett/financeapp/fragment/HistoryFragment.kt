package com.lawlett.financeapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.PagerAdapter
import com.lawlett.financeapp.databinding.FragmentHistoryBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {
    private val tabTitle = arrayOf("Категории", "Месяц")
    private val binding: FragmentHistoryBinding by viewBinding()
    private lateinit var pagerAdapter: PagerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initTab()
    }

    private fun initAdapter() {
        pagerAdapter = PagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.fragmentPager.adapter = pagerAdapter
    }

    private fun initTab() {
        binding.fragmentTab.setTabTextColors(
            Color.parseColor(getString(R.string.black)),
            Color.parseColor(
                getString(
                    R.string.green
                )
            )
        )
        TabLayoutMediator(binding.fragmentTab, binding.fragmentPager) { tab, pos ->
            tab.text = tabTitle[pos]
        }.attach()
    }
}