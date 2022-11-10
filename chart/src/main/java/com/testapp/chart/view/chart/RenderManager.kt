package com.testapp.chart.view.chart

import com.testapp.chart.view.Point
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator

/**
 * @author aliakseicherniakovich
 */
class RenderManager {

    private val splineInterpolator: SplineInterpolator by lazy { SplineInterpolator() }

    fun renderBezier(resolution: Float = DEFAULT_CHART_RESOLUTION, points: MutableList<Point>): List<Point> {
        val result = mutableListOf<Point>()
        val xArray = DoubleArray(points.size)
        val yArray = DoubleArray(points.size)
        points.forEachIndexed { index, pointF ->
            var x = pointF.x.toDouble()
            var y = pointF.y.toDouble()
            if (xArray.isContain(x)) x += DEFAULT_DELTA_PRECESSION
            if (yArray.isContain(y)) y += DEFAULT_DELTA_PRECESSION
            xArray[index] = x
            yArray[index] = y
        }
        val interpolate = splineInterpolator.interpolate(xArray, yArray)

        var i = points.minOf { it.x }
        val end = points.maxOf { it.x }

        while (i < end) {
            val x = i
            val y = interpolate.value(i.toDouble())
            result.add(Point(x, y.toFloat()))
            i += resolution
        }

        return result
    }

    private fun DoubleArray.isContain(number: Double): Boolean = find { it == number } != null

    private companion object {
        const val DEFAULT_CHART_RESOLUTION = 1f
        const val DEFAULT_DELTA_PRECESSION = 0.001f
    }
}
