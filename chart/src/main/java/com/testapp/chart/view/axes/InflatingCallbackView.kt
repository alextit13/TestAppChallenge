package com.testapp.chart.view.axes

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.testapp.chart.view.Point
import com.testapp.chart.view.SortedPointList

/**
 * @author aliakseicherniakovich
 */
abstract class InflatingCallbackView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    protected var data: SortedPointList = SortedPointList()
        private set

    protected var realCanvasWidth: Int = VIEW_NON_INFLATE_SIZE
    protected var realCanvasHeight: Int = VIEW_NON_INFLATE_SIZE

    private var isViewInflating = false

    open fun setPointsData(data: List<Pair<Float, Float>>) {
        this.data = SortedPointList().apply { this.points = PointMapper.convert(data) }
        waitInflating()
    }

    private fun waitInflating() {
        if (isViewInflating) {
            onViewInflated()
            return
        }
    }

    abstract fun onViewInflated()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        realCanvasWidth = width
        realCanvasHeight = height

        isViewInflating = true
        if (data.points.isNotEmpty()) {
            onViewInflated()
        }
    }

    override fun onDetachedFromWindow() {
        isViewInflating = false
        super.onDetachedFromWindow()
    }

    private companion object {
        const val VIEW_NON_INFLATE_SIZE = 0
    }
}
