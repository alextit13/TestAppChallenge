package com.testapp.chart.view.axes

import com.testapp.chart.view.Point

/**
 * @author aliakseicherniakovich
 */
object PointMapper {
    fun convert(pairs: List<Pair<Float, Float>>): List<Point> {
        return pairs.map {
            Point(it.first, it.second)
        }
    }
}
