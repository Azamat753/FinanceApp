package com.lawlett.financeapp.fragment

import android.os.Bundle
import android.view.View
import com.lawlett.domain.model.BalanceModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.HistoryCategoryAdapter
import com.lawlett.financeapp.databinding.FragmentCategoryBinding
import com.lawlett.financeapp.presenter.CategoryPresenter
import com.lawlett.financeapp.utils.gone
import com.lawlett.financeapp.utils.visible
import com.lawlett.view.CategoryView
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment :
    MvpAppCompatFragment(R.layout.fragment_category), CategoryView {
    private val binding: FragmentCategoryBinding by viewBinding()

    private lateinit var adapter: HistoryCategoryAdapter

    @InjectPresenter
    lateinit var presenter: CategoryPresenter

    @Inject
    lateinit var hiltPresenter: CategoryPresenter

    @ProvidePresenter
    fun providePresenter(): CategoryPresenter {
        return hiltPresenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initPresenter()
    }

    private fun initPresenter() {
        presenter.initData()
    }

    private fun initAdapter() {
        adapter = HistoryCategoryAdapter()
        binding.rvCategory.adapter = adapter
    }

    override fun initData() {
        updateDate(presenter.getAllCategoryList())
        presenter.checkList(presenter.getAllCategoryList())
    }

    override fun txtGone() = binding.includeEmpty.root.gone()

    override fun txtVisible() = binding.includeEmpty.root.visible()

    private fun updateDate(allCategoryList: List<BalanceModel>) {
        adapter.addList(allCategoryList)
    }


}