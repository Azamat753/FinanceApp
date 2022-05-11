package com.lawlett.financeapp.fragment

import android.os.Bundle
import android.view.View
import com.lawlett.domain.model.BalanceModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.HistoryMonthAdapter
import com.lawlett.financeapp.databinding.FragmentMonthBinding
import com.lawlett.financeapp.presenter.MonthPresenter
import com.lawlett.financeapp.utils.gone
import com.lawlett.financeapp.utils.visible
import com.lawlett.view.MonthView
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class MonthFragment : MvpAppCompatFragment(R.layout.fragment_month), MonthView {

    private val binding: FragmentMonthBinding by viewBinding()

    @InjectPresenter
    lateinit var presenter: MonthPresenter

    @Inject
    lateinit var hiltPresenter: MonthPresenter

    @ProvidePresenter
    fun providePresenter(): MonthPresenter {
        return hiltPresenter
    }

    private lateinit var adapter: HistoryMonthAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initPresenter()
    }

    private fun initPresenter() = presenter.initData()

    private fun initAdapter() {
        adapter = HistoryMonthAdapter()
        binding.rvMonth.adapter = adapter
    }

    override fun initData() {
        updateDate(presenter.getMonthList())
        presenter.checkList(presenter.getMonthList())
    }

    private fun updateDate(list: List<BalanceModel>) {
        adapter.addList(list)
    }

    override fun txtGone() = binding.includeEmpty.root.gone()

    override fun txtVisible() = binding.includeEmpty.root.visible()
}