package com.testapp.chart.view.gesture

/**
 * @author aliakseicherniakovich
 */
sealed class Direction {

    sealed class Scroll : Direction() {
        object Left : Scroll()
        object Right : Scroll()
    }

    sealed class Scale : Direction() {
        object Plus : Scale()
        object Minus : Scale()
    }
}
