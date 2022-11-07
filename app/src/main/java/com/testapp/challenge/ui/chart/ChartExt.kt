package com.testapp.challenge.ui.chart

import com.testapp.challenge.model.network.dto.Point

/**
 * @author aliakseicherniakovich
 */
fun List<Point>.mapToPairs(): List<Pair<Float, Float>> = map { Pair(it.x, it.y) }

fun List<Pair<Float, Float>>.mapToPoints(): List<Point> = map { Point(it.first, it.second) }
