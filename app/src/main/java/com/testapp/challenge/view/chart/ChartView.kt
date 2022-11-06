package com.testapp.challenge.view.chart

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.testapp.challenge.model.network.dto.Point
import com.testapp.challenge.view.axes.AxesView

/**
 * @author aliakseicherniakovich
 */
class ChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AxesView(context, attrs) {

    private var mode: ChartViewMode = ChartViewMode.defaultMode
    private val pairPoints = mutableListOf<Pair<Point, Point?>>()
    private var extremum: ExtremumLevel = ExtremumLevel(ExtremumPoint(0f, 0f), ExtremumPoint(0f, 0f))

    fun getBitmap(): Bitmap {
        val capture: Bitmap = Bitmap.createBitmap(realCanvasWidth, realCanvasHeight, Bitmap.Config.ARGB_8888)
        capture.setHasAlpha(true)
        val bitmapCanvas = Canvas(capture)
        layout(left, top, right, bottom)
        draw(bitmapCanvas)
        return capture
    }

    override fun onViewInflated() {
        super.onViewInflated()
        calculateExtremums()
        prepareChart()

        invalidate()
    }

    private fun calculateExtremums() {
        val minX = data.points.minOf { it.x }
        val maxX = data.points.maxOf { it.x }
        val minY = data.points.minOf { it.y }
        val maxY = data.points.maxOf { it.y }
        extremum = ExtremumLevel(ExtremumPoint(minX, maxX), ExtremumPoint(minY, maxY))
    }

    private fun prepareChart() {
        val points = toRelativeCoordinates(data.points)

        for (i in points.indices) {
            val start = points[i]
            val end = try {
                points[i + 1]
            } catch (e: IndexOutOfBoundsException) {
                points[i]
            }
            pairPoints.add(Pair(start, end))
        }
    }

    private fun toRelativeCoordinates(points: List<Point>): List<Point> {
        val coordinates = mutableListOf<Point>()
        val inOnePxPartsX = (realCanvasWidth - (PADDING * 2)) / (extremum.maxX() - extremum.minX()) // right and left paddings
        val inOnePxPartsY = (realCanvasHeight - (PADDING * 2)) / (extremum.maxY() - extremum.minY()) // top and bottom paddings

        points.forEach {
            val x = ((it.x - extremum.minX()) * inOnePxPartsX) + PADDING
            val y = (realCanvasHeight - ((it.y - extremum.minY()) * inOnePxPartsY)) - PADDING
            coordinates.add(Point(x, y))
        }
        return coordinates
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        pairPoints.forEach {
            val start = it.first
            val end = it.second ?: return

            canvas?.drawLine(start.x, start.y, end.x, end.y, chartPaint)
            canvas?.drawPoint(start.x, start.y, pointPaint)
        }
    }

    private companion object {
        val chartPaint: Paint = Paint().apply {
            color = Color.GRAY
            strokeWidth = CHART_LINE_WIDTH
            style = Paint.Style.STROKE
        }
        val pointPaint: Paint = Paint().apply {
            color = Color.DKGRAY
            strokeWidth = CHART_POINTS_WIDTH
            style = Paint.Style.FILL
        }
        const val CHART_LINE_WIDTH = 2f
        const val CHART_POINTS_WIDTH = 8f
    }
}
