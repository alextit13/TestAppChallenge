package com.testapp.chart.view.chart

import android.graphics.Path
import com.testapp.chart.view.Point

/**
 * @author aliakseicherniakovich
 */
class RenderManager {

    private val bezierIntensity = 0.5f

    fun renderBezier(
        points: MutableList<Point>,
        mWidth: Int,
        padding: Float
    ): Path {
        val chartValues = points.map { it.y }
        val chartValuesX = points.map { it.x }

        if(chartValues.isEmpty()) return Path()

        val chartPointStep = mWidth / chartValues.count()

        val chartPath = Path().apply {
            var xStart = padding

            moveTo(xStart, chartValues.first())

            for (index in 1 until chartValues.size) {
                val y = chartValues[index]

                val xPrev = chartValuesX[index - 1]
                val x = chartValuesX[index]

                val prevDy: Float
                val curDy: Float

                xStart += (x - xPrev)

                val prevVal: Float = chartValues[index - 1]

                val nextVal: Float = if(index < chartValues.size - 1) {
                    chartValues[index + 1]
                } else {
                    y
                }

                val prevDx: Float = (xStart - (xStart - chartPointStep)) * bezierIntensity
                prevDy = (y - prevVal) * bezierIntensity
                val curDx: Float = ((xStart + chartPointStep) - xStart) * bezierIntensity
                curDy = (nextVal - y) * bezierIntensity

                val x1 = (xStart - chartPointStep) + prevDx
                val y1 = prevVal + prevDy
                val x2 = xStart - curDx
                val y2 = y - curDy
                val x3 = xStart

                this.cubicTo(x1, y1, x2, y2, x3, y)
            }
        }
        return chartPath
    }
}
