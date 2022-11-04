package com.testapp.challenge.view.chart

import android.content.Context
import android.util.AttributeSet
import com.testapp.challenge.model.network.dto.Point
import com.testapp.challenge.view.axes.AxesView

/**
 * @author aliakseicherniakovich
 */
class ChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AxesView(context, attrs) {

    fun setPoints(points: List<Point>) {
        setPointsData(points)
    }

    fun setMode(mode: ChartViewMode) {
        // todo
    }

    private fun drawChart(data: List<Point>, mode: ChartViewMode) {
        // todo
    }

    private fun drawPoints() {
        // todo
    }

    private fun drawChart() {
        // todo
    }
}
