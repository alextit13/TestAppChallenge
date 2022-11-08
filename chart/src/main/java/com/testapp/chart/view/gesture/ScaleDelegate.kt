package com.testapp.chart.view.gesture

import android.view.ScaleGestureDetector
import kotlin.math.abs

/**
 * @author aliakseicherniakovich
 */
class ScaleDelegate {

    fun handleScaleAction(detector: ScaleGestureDetector, onActionEvent: (Direction) -> Unit) {
        if (abs(detector.previousSpan - detector.currentSpan) >= 20) {
            val direction = if (detector.previousSpan > detector.currentSpan) {
                Direction.Scale.Minus
            } else {
                Direction.Scale.Plus
            }
            onActionEvent(direction)
        }
    }
}
