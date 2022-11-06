package com.testapp.challenge.view.axes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import com.testapp.challenge.model.network.dto.Point
import com.testapp.challenge.view.SortedPointList

/**
 * @author aliakseicherniakovich
 */
open class AxesView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : InflatingCallbackView(context, attrs, defStyleAttr) {

    protected var data: SortedPointList = SortedPointList()
        private set

    private var gridHorizontal: List<Pair<String, Float>> = listOf()
    private var gridVertical: List<Pair<String, Float>> = listOf()

    protected fun setPointsData(data: List<Point>) {
        this.data = SortedPointList().apply { this.points = data }

        waitInflating {
            calculateGrid()
            invalidate()
        }
    }

    private fun calculateGrid() {

        gridHorizontal = data.calculate(realCanvasHeight, Direction.Horizontal)
        gridVertical = data.calculate(realCanvasWidth, Direction.Vertical)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // X axes
        canvas?.drawLine(PADDING, realCanvasHeight - PADDING, realCanvasWidth.toFloat(), realCanvasHeight - PADDING, axesPaint)
        // Y axes
        canvas?.drawLine(PADDING, realCanvasHeight - PADDING, PADDING, PADDING, axesPaint)
        // grid vertical
        gridVertical.forEach {
            canvas?.drawLine(it.second + PADDING, PADDING, it.second + PADDING, realCanvasHeight - PADDING, gridPaint)
            // legend horizontal
            canvas?.drawText(it.first, it.second + PADDING, realCanvasHeight.toFloat(), legendPaint)
        }
        // grid horizontal
        gridHorizontal.forEach {
            canvas?.drawLine(PADDING, it.second - PADDING, realCanvasWidth.toFloat(), it.second - PADDING, gridPaint)
            // legend vertical
            canvas?.drawText(it.first, START_POINT_X_LEGEND, realCanvasHeight - it.second - PADDING, legendPaint)
        }
    }

    private companion object {
        const val PADDING = 50f
        const val STROKE_AXES_WIDTH = 2f
        const val STROKE_GRID_WIDTH = 1f
        const val LEGEND_TEXT_SIZE = 22f
        const val PHASE_GRID_LINES = 0f
        const val START_POINT_X_LEGEND = 20f
        val INTERVALS_GRID_LINES = floatArrayOf(5f, 10f, 15f, 20f)

        val axesPaint: Paint = Paint().apply {
            color = Color.GRAY
            strokeWidth = STROKE_AXES_WIDTH
        }
        val gridPaint: Paint = Paint().apply {
            color = Color.LTGRAY
            strokeWidth = STROKE_GRID_WIDTH
            pathEffect = DashPathEffect(INTERVALS_GRID_LINES, PHASE_GRID_LINES)
        }
        val legendPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.DKGRAY
            textSize = LEGEND_TEXT_SIZE
            textAlign = Paint.Align.CENTER
            style = Paint.Style.STROKE
        }
    }
}
