package com.testapp.chart.view.axes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import com.testapp.chart.view.gesture.GestureView

/**
 * @author aliakseicherniakovich
 */
open class AxesView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : GestureView(context, attrs) {

    private var gridV: List<Pair<String, Float>> = listOf()
    private var gridH: List<Pair<String, Float>> = listOf()

    override fun onViewInflated() {
        calculateGrid()
        invalidate()
    }

    private fun calculateGrid() {
        gridV = data.calculateVertical(realCanvasWidth - 2 * PADDING.toInt()) // top and bottom paddings
        gridH = data.calculateHorizontal(realCanvasHeight - 2 * PADDING.toInt()) // left and right paddings
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // X axes
        canvas?.drawLine(PADDING, realCanvasHeight - PADDING, realCanvasWidth.toFloat(), realCanvasHeight - PADDING, axesPaint)
        // Y axes
        canvas?.drawLine(PADDING, realCanvasHeight - PADDING, PADDING, 0f, axesPaint)
        // grid vertical
        gridV.forEach {
            canvas?.drawLine(it.second + PADDING, 0f, it.second + PADDING, realCanvasHeight - PADDING, gridPaint)
            // legend vertical
            canvas?.drawText(it.first, it.second + PADDING, realCanvasHeight.toFloat(), legendPaint)
        }
        // grid horizontal
        gridH.forEach {
            canvas?.drawLine(PADDING, realCanvasHeight - it.second - PADDING, realCanvasWidth.toFloat(), realCanvasHeight - it.second - PADDING, gridPaint)
            // legend horizontal
            canvas?.drawText(it.first, START_POINT_X_LEGEND, realCanvasHeight - it.second - PADDING, legendPaint)
        }
    }

    companion object {
        const val PADDING = 50f
        const val START_POINT_X_LEGEND = 20f
        private const val STROKE_AXES_WIDTH = 2f
        private const val STROKE_GRID_WIDTH = 1f
        private const val LEGEND_TEXT_SIZE = 22f
        private const val PHASE_GRID_LINES = 0f
        private val INTERVALS_GRID_LINES = floatArrayOf(5f, 10f, 15f, 20f)

        private val axesPaint: Paint = Paint().apply {
            color = Color.GRAY
            strokeWidth = STROKE_AXES_WIDTH
        }
        private val gridPaint: Paint = Paint().apply {
            color = Color.LTGRAY
            strokeWidth = STROKE_GRID_WIDTH
            pathEffect = DashPathEffect(INTERVALS_GRID_LINES, PHASE_GRID_LINES)
        }
        private val legendPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.DKGRAY
            textSize = LEGEND_TEXT_SIZE
            textAlign = Paint.Align.CENTER
            style = Paint.Style.STROKE
        }
    }
}
