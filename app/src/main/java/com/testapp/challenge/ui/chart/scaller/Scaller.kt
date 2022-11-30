package com.testapp.challenge.ui.chart.scaller

import com.testapp.challenge.model.network.dto.Point

/**
 * @author aliakseicherniakovich
 */
interface Scaller {

    fun observe(data: List<Point>, block: (List<Point>) -> Unit)

    fun plus()

    fun minus()

    fun left()

    fun right()
}
