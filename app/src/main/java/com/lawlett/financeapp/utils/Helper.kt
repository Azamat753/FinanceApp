package com.lawlett.financeapp.utils

import android.app.Activity
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.core.view.isVisible
import com.lawlett.financeapp.R
import com.takusemba.spotlight.OnSpotlightListener
import com.takusemba.spotlight.OnTargetListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.effet.RippleEffect
import com.takusemba.spotlight.shape.RoundedRectangle


const val UTC = "UTC"

const val BALANCE = "balance"

const val HISTORY = "history"

const val PLAN = "plan"

const val SHARED = "Shared"

const val CARD_PLAN = "card_plan"

fun View.gone() {
    this.isVisible = false
}

fun View.visible() {
    this.isVisible = true
}

fun setSpotLightTarget(targetView: View, backLayoutView: View, description: String): Target {
    val target = Target.Builder().apply {
        setAnchor(targetView)
        setShape(RoundedRectangle(targetView.height.toFloat(), targetView.width.toFloat(), 30F))
        setEffect(RippleEffect(100f, 200f, Color.argb(30, 124, 255, 90)))
        setOverlay(backLayoutView)
        setOnTargetListener(object : OnTargetListener {
            override fun onStarted() {
                backLayoutView.findViewById<TextView>(R.id.text_target).text = description
            }

            override fun onEnded() {
            }
        })
    }.build()
    return target
}

fun setSpotLightBuilder(activity: Activity, targets: ArrayList<Target>, backLayoutView: View) {
    Handler(Looper.getMainLooper()).postDelayed({
        val spotlight = Spotlight.Builder(activity).apply {
            setTargets(targets)
            setBackgroundColor(R.color.background)
            setDuration(1000L)
            setAnimation(DecelerateInterpolator(2f))
            setOnSpotlightListener(object : OnSpotlightListener {
                override fun onStarted() {

                }

                override fun onEnded() {

                }
            })
        }.build()


        spotlight.start()

        backLayoutView.findViewById<TextView>(R.id.next).setOnClickListener { spotlight.next() }
    }, 1000)
}