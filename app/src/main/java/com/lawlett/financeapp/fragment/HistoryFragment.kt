package com.lawlett.financeapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.PagerAdapter
import com.lawlett.financeapp.databinding.FragmentHistoryBinding
import com.lawlett.financeapp.presenter.CategoryPresenter
import com.lawlett.financeapp.presenter.HistoryPresenter
import com.lawlett.view.HistoryView
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : MvpAppCompatFragment(R.layout.fragment_history), HistoryView {
    private val tabTitle by lazy { arrayOf(getString(R.string.category), getString(R.string.month)) }
    private val binding: FragmentHistoryBinding by viewBinding()
    private lateinit var pagerAdapter: PagerAdapter


    @Inject
    lateinit var hiltPresenterCategory: CategoryPresenter


    @InjectPresenter
    lateinit var presenter: HistoryPresenter

    @Inject
    lateinit var hiltPresenter: HistoryPresenter

    @ProvidePresenter
    fun providePresenter(): HistoryPresenter {
        return hiltPresenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initAdapter()
        initTab()
    }

    private fun initData() {
        presenter.getData()
    }


    private fun initAdapter() {
        pagerAdapter =
            PagerAdapter(requireActivity().supportFragmentManager, lifecycle)
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

    override fun getData() {
        initFragments()
    }

    private fun initFragments() {
            pagerAdapter.add(0, CategoryFragment())
            pagerAdapter.add(1, MonthFragment())
    }


}