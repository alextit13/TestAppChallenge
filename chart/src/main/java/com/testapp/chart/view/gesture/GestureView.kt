package com.testapp.chart.view.gesture

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import com.testapp.chart.view.axes.InflatingCallbackView

/**
 * @author aliakseicherniakovich
 */
open class GestureView(context: Context, attrs: AttributeSet? = null) :
    InflatingCallbackView(context, attrs) {

    var gestureViewListener: ((Direction) -> Unit)? = null
    private val scrollDelegate = ScrollDelegate()
    private val scaleDelegate = ScaleDelegate()

    private val gestureListener: GestureDetector.OnGestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            gestureViewListener?.let { scrollDelegate.handleScrollAction(e1, e2, distanceX, it) }

            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    }
    private val scaleListener: ScaleGestureDetector.OnScaleGestureListener = object : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            gestureViewListener?.let { scaleDelegate.handleScaleAction(detector, it) }
            return true
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean = true

        override fun onScaleEnd(detector: ScaleGestureDetector) { }
    }

    private val scaleGestureDetector: ScaleGestureDetector = ScaleGestureDetector(context, scaleListener)

    private val gestureDetector = GestureDetector(context, gestureListener)

    override fun onViewInflated() {
        // no-op
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { ev ->
            gestureDetector.onTouchEvent(ev)
            scaleGestureDetector.onTouchEvent(ev)
        }
        return true
    }
}
