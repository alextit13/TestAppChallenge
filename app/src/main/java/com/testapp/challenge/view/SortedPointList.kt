package com.testapp.challenge.view

import com.testapp.challenge.model.network.dto.Point

/**
 * @author aliakseicherniakovich
 */
class SortedPointList {
    var points: List<Point> = listOf()
        set(value) {
            field = sortPointsBy(value, ComparatorParameter.X)
        }

    fun sortPointsBy(comparatorParameter: ComparatorParameter): List<Point> =
        sortPointsBy(points, comparatorParameter)

    private fun sortPointsBy(data: List<Point>, comparatorParameter: ComparatorParameter): List<Point> =
        when (comparatorParameter) {
            ComparatorParameter.X -> data.sortedBy { it.x }
            ComparatorParameter.Y -> data.sortedBy { it.y }
        }

    enum class ComparatorParameter {
        X,
        Y
    }
}
