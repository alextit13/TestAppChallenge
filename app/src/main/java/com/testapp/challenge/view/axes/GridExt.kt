package com.testapp.challenge.view.axes

import android.util.Log
import com.testapp.challenge.view.SortedPointList

/**
 * @author aliakseicherniakovich
 */
internal fun SortedPointList.calculateStrokeGrid(realCanvasHeight: Int): List<Pair<String, Float>> {
    Log.d("LOG_TAG", "calculateStrokeGrid. realCanvasHeight = $realCanvasHeight")
    val gridStep = realCanvasHeight / COUNT_LINES_IN_GRID_HORIZONTAL
    val result = mutableListOf<Pair<String, Float>>()
    val diapason = this.points.minOf { it.y } .. this.points.maxOf { it.y }
    val diapasonSet = mutableMapOf<Int, Int>()
    for ((iterator, i) in (diapason.start.toInt() .. diapason.endInclusive.toInt()).withIndex()) {
        diapasonSet[iterator] = i
    }

    val listDeltas = diapasonSet.values.chunked(diapasonSet.size / COUNT_LINES_IN_GRID_HORIZONTAL)
    for (i in 0 until COUNT_LINES_IN_GRID_HORIZONTAL) {
        val absolutePositionLabel = listDeltas[i].first()
        val y = (i + 1) * gridStep
        result.add(Pair("$absolutePositionLabel", y.toFloat()))
    }

    return result
}

internal fun SortedPointList.calculateColumnGrid(realCanvasWidth: Int): List<Pair<String, Float>> {
    val gridStep = realCanvasWidth / COUNT_LINES_IN_GRID_VERTICAL
    val result = mutableListOf<Pair<String, Float>>()
    val diapason = this.points.minOf { it.x } .. this.points.maxOf { it.x }
    val diapasonSet = mutableMapOf<Int, Int>()
    for ((iterator, i) in (diapason.start.toInt() .. diapason.endInclusive.toInt()).withIndex()) {
        diapasonSet[iterator] = i
    }

    val listDeltas = diapasonSet.values.chunked(diapasonSet.size / COUNT_LINES_IN_GRID_VERTICAL)
    for (i in 0 until COUNT_LINES_IN_GRID_VERTICAL) {
        val absolutePositionLabel = listDeltas[i].first()
        val x = (i) * gridStep
        result.add(Pair("$absolutePositionLabel", x.toFloat()))
    }

    return result
}

private const val DEFAULT_COUNT_LINES_IN_GRID = 9
private const val COUNT_LINES_IN_GRID_HORIZONTAL = DEFAULT_COUNT_LINES_IN_GRID
private const val COUNT_LINES_IN_GRID_VERTICAL = DEFAULT_COUNT_LINES_IN_GRID
