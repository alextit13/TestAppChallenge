package com.testapp.chart.view.axes

import com.testapp.chart.view.Point
import com.testapp.chart.view.SortedPointList
import kotlin.math.roundToInt

/**
 * @author aliakseicherniakovich
 */
internal fun SortedPointList.calculateVertical(
    canvasWidth: Int
): List<Pair<String, Float>> = calculate(canvasWidth, Direction.Vertical)

internal fun SortedPointList.calculateHorizontal(
    canvasHeight: Int
): List<Pair<String, Float>> = calculate(canvasHeight, Direction.Horizontal)

private fun SortedPointList.calculate(size: Int, direction: Direction): List<Pair<String, Float>> {
    val gridStep = size/ DEFAULT_COUNT_LINES_IN_GRID
    val result = mutableListOf<Pair<String, Float>>()
    val start = this.points.getMin(direction)
    val end = this.points.getMax(direction)

    val gridLabelStep = (end - start) / DEFAULT_COUNT_LINES_IN_GRID

    for (i in 0 .. DEFAULT_COUNT_LINES_IN_GRID) {
        val label = "${(start + (gridLabelStep * i)).roundToInt()}"
        val coordinate = i * gridStep.toFloat()
        result.add(Pair(label, coordinate))
    }

    return result
}

private fun List<Point>.getMin(direction: Direction): Float =
    minOf { it.getExtreme(direction) }

private fun List<Point>.getMax(direction: Direction): Float =
    maxOf { it.getExtreme(direction) }

private fun Point.getExtreme(direction: Direction): Float = when (direction) {
    Direction.Horizontal -> y
    Direction.Vertical -> x
}

enum class Direction {
    Horizontal,
    Vertical
}

private const val DEFAULT_COUNT_LINES_IN_GRID = 5
