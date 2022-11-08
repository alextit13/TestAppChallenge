package com.testapp.chart.view.chart

/**
 * @author aliakseicherniakovich
 */
enum class ChartViewMode {
    Linear,
    Bezier;

    companion object {
        val defaultMode = Linear
    }
}
