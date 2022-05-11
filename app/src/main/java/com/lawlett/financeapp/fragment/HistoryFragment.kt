package com.lawlett.financeapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.PagerAdapter
import com.lawlett.financeapp.databinding.FragmentHistoryBinding
import com.lawlett.financeapp.presenter.CategoryPresenter
import com.lawlett.financeapp.presenter.HistoryPresenter
import com.lawlett.financeapp.utils.Pref
import com.lawlett.financeapp.utils.setSpotLightBuilder
import com.lawlett.financeapp.utils.setSpotLightTarget
import com.lawlett.view.HistoryView
import com.redmadrobot.extensions.viewbinding.viewBinding
import com.takusemba.spotlight.Target
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : MvpAppCompatFragment(R.layout.fragment_history), HistoryView {
    private val tabTitle by lazy {
        arrayOf(
            getString(R.string.category),
            getString(R.string.month)
        )
    }

    private val binding: FragmentHistoryBinding by viewBinding()

    private lateinit var pagerAdapter: PagerAdapter

    @Inject
    lateinit var pref: Pref

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
        checkHint()
    }

    private fun initData() {
        presenter.getData()
    }

    private fun checkHint() {
        if (!pref.isShowHistory()) {
            val target = ArrayList<Target>()
            val root = FrameLayout(requireContext())
            val first = layoutInflater.inflate(R.layout.layout_target, root)
            val view = View(requireContext())
            Handler(Looper.getMainLooper()).postDelayed({
                val firstSpot = setSpotLightTarget(
                    view, first, "\n\n\n ${getString(R.string.history)}" +
                            " \n\n ${getString(R.string.window_for_watch_costs)}"
                )
                target.add(firstSpot)
                setSpotLightBuilder(requireActivity(), target, first)
            }, 100)
            pref.showHistory()
        }
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