package com.lawlett.financeapp.fragment

import android.os.Bundle
import android.view.View
import com.lawlett.financeapp.R
import com.lawlett.financeapp.databinding.FragmentIntroBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import moxy.MvpAppCompatFragment

class IntroFragment : MvpAppCompatFragment(R.layout.fragment_intro) {

    private val binding: FragmentIntroBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}