package com.testapp.challenge.view.chart

/**
 * @author aliakseicherniakovich
 */
enum class ChartViewMode {
    Linear,
    Bezier;

    companion object {
        val defaultMode = ChartViewMode.Linear
    }
}
