package com.lawlett.financeapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val list: MutableList<Fragment> = mutableListOf()
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]

    fun add(index: Int, fragment: Fragment) {
        list.add(index, fragment)
        notifyItemChanged(index)
    }
}