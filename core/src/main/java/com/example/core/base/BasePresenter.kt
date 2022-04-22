package com.example.core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.coroutines.CoroutineContext

open class BasePresenter<View : MvpView?> : MvpPresenter<View>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var job = Job()

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}