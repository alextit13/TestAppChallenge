package com.testapp.challenge.ui.chart.scaller

import com.testapp.challenge.model.network.dto.Point
import com.testapp.challenge.ui.chart.mapToPairs
import com.testapp.challenge.ui.chart.mapToPoints

/**
 * @author aliakseicherniakovich
 */
class ScallerImpl: Scaller {

    private lateinit var observer: (List<Point>) -> Unit
    private var points: MutableList<Pair<Float, Float>> = mutableListOf()
    private lateinit var caret: Caret

    override fun observe(data: List<Point>, block: (List<Point>) -> Unit) {
        this.observer = block
        points = data.mapToPairs().toMutableList()
        caret = Caret(points.size) {
            val start = it.first
            val end = it.second

            observer(points.subList(start, end).mapToPoints())
        }
    }

    override fun plus() {
        caret.plus()
    }

    override fun minus() {
        caret.minus()
    }

    override fun left() {
        caret.left()
    }

    override fun right() {
        caret.right()
    }

    class Caret(private val initialLength: Int, private val block: (Pair<Int, Int>) -> Unit) {

        private var length: Int = initialLength
        private var leftIndex = 0
        private var rightIndex = initialLength

        fun plus() {
            if (length == MIN_SCALE) return
            length -= COUNT_STEP_IN_ONE_ACTION * 2
            leftIndex++
            rightIndex--

            block(Pair(leftIndex, rightIndex))
        }

        fun minus() {
            if (length == initialLength) return
            if (leftIndex == 0) {
                length += COUNT_STEP_IN_ONE_ACTION
                rightIndex++
            } else if (rightIndex == initialLength) {
                length += COUNT_STEP_IN_ONE_ACTION
                leftIndex--
            } else {
                leftIndex--
                rightIndex++
                length += COUNT_STEP_IN_ONE_ACTION * 2
            }

            block(Pair(leftIndex, rightIndex))
        }

        fun left() {
            if (leftIndex == 0) return
            leftIndex--
            rightIndex--

            block(Pair(leftIndex, rightIndex))
        }

        fun right() {
            if (rightIndex == initialLength) return
            leftIndex++
            rightIndex++

            block(Pair(leftIndex, rightIndex))
        }

        private companion object {
            const val MIN_SCALE = 2
            const val COUNT_STEP_IN_ONE_ACTION = 1
        }
    }
}
