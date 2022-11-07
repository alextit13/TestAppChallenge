package com.testapp.chart.view.scale

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.testapp.chart.view.axes.InflatingCallbackView

/**
 * @author aliakseicherniakovich
 */
open class GestureScaleView(context: Context, attrs: AttributeSet? = null) :
    InflatingCallbackView(context, attrs) {

    override fun onViewInflated() {
        // no-op
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}
