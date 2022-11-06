package com.testapp.challenge.view.axes

import com.testapp.challenge.model.network.dto.Point
import com.testapp.challenge.view.SortedPointList
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * @author aliakseicherniakovich
 */
internal fun SortedPointList.calculate(size: Int, direction: Direction): List<Pair<String, Float>> {
    val gridStep = size / DEFAULT_COUNT_LINES_IN_GRID
    val result = mutableListOf<Pair<String, Float>>()
    val start = this.points.getMin(direction)
    val end = this.points.getMax(direction)

    val gridLabelStep =
        ((abs(start) + abs(end)).toDouble() / DEFAULT_COUNT_LINES_IN_GRID).roundToInt()
    for (i in 0 until DEFAULT_COUNT_LINES_IN_GRID) {
        val label = (start + (gridLabelStep * i)).toString()
        val coordinate = i * gridStep.toFloat()
        result.add(Pair(label, coordinate))
    }

    return result
}

private fun List<Point>.getMin(direction: Direction): Int =
    minOf { it.getExtreme(direction) }.toInt()

private fun List<Point>.getMax(direction: Direction): Int =
    maxOf { it.getExtreme(direction) }.toInt()

private fun Point.getExtreme(direction: Direction): Float = when (direction) {
    Direction.Horizontal -> y
    Direction.Vertical -> x
}

enum class Direction {
    Horizontal,
    Vertical
}

private const val DEFAULT_COUNT_LINES_IN_GRID = 9
