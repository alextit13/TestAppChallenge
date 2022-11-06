package com.testapp.challenge.view.chart

import android.graphics.Path
import com.testapp.challenge.model.network.dto.Point

/**
 * @author aliakseicherniakovich
 */
class RenderManager {

    fun renderBezier(pairPoints: MutableList<Pair<Point, Point?>>): List<Path> {
        val result = mutableListOf<Path>()
        pairPoints.forEach {
            val start = it.first
            val end = it.second ?: start

            val x1 = start.x
            val y1 = start.y

            val x3 = end.x
            val y3 = end.y

            // todo calculate this
            val x2 = x3 + 10
            val y2 = y3 + 10

            val path = Path().apply {
                moveTo(x1, y1)
                cubicTo(x1, y1, x2, y2, x3, y3)
            }

            result.add(path)
        }
        return result
    }
}
