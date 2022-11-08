package com.testapp.chart.view.gesture

import android.view.MotionEvent
import kotlin.math.abs

/**
 * @author aliakseicherniakovich
 */
internal class ScrollDelegate {

    private var downEvent: MotionEvent? = null
    private var startDragPoint = 0f

    fun handleScrollAction(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        onAction: (ScrollDirection) -> Unit
    ) {
        detectDownEvent(e1, e2)

        if (downEvent != null) {
            startDragPoint += abs(distanceX)
            if (startDragPoint >= TICK_DISTANCE) {
                startDragPoint = 0f
                val startDragX = downEvent?.x ?: 0f
                val endDragX = e2.x
                if (startDragX < endDragX) {
                    onAction.invoke(ScrollDirection.Left)
                } else if (startDragX > endDragX) {
                    onAction.invoke(ScrollDirection.Right)
                }
            }
        }
    }

    private fun detectDownEvent(e1: MotionEvent, e2: MotionEvent) {
        if (downEvent == null) {
            if (e1.action == MotionEvent.ACTION_DOWN) {
                downEvent = e1
                startDragPoint = e1.x
            }
        } else {
            if (e2.action == MotionEvent.ACTION_UP) {
                downEvent = null
                startDragPoint = 0f
            }
        }
    }

    private companion object {
        const val TICK_DISTANCE = 100f
    }
}
