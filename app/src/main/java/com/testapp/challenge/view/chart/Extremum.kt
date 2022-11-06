package com.testapp.challenge.view.chart

import com.testapp.challenge.model.network.dto.Point

/**
 * @author aliakseicherniakovich
 */
class ExtremumLevel(private val x: ExtremumPoint, private val y: ExtremumPoint) {
    fun minX() = x.min
    fun maxX() = x.max

    fun minY() = y.min
    fun maxY() = y.max
}

class ExtremumPoint(val min: Float, val max: Float)
