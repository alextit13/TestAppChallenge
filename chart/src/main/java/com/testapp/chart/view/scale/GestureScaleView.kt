package com.testapp.chart.view.scale

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import com.testapp.chart.view.axes.InflatingCallbackView

/**
 * @author aliakseicherniakovich
 */
open class GestureScaleView(context: Context, attrs: AttributeSet? = null) : InflatingCallbackView(context, attrs) {

    override fun onViewInflated() {
        // no-op
    }

    private var gestureDetector: ScaleGestureDetector? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        gestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.OnScaleGestureListener {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                Log.d("LOG_TAG", "onScale")
                return true
            }

            override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
                Log.d("LOG_TAG", "onScaleBegin")
                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector) {
                Log.d("LOG_TAG", "onScaleEnd")
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return event?.let { gestureDetector?.onTouchEvent(it) } ?: false
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}
