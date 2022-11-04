package com.testapp.challenge.view.axes

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @author aliakseicherniakovich
 */
open class InflatingCallbackView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    protected var realCanvasWidth: Int = VIEW_NON_INFLATE_SIZE
    protected var realCanvasHeight: Int = VIEW_NON_INFLATE_SIZE

    private var isViewInflating = false
    private var onViewInflated: (() -> Unit)? = null

    fun waitInflating(block: () -> Unit) {
        if (isViewInflating) {
            block()
            return
        }
        onViewInflated = block
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        realCanvasWidth = width
        realCanvasHeight = height

        isViewInflating = true
        onViewInflated?.invoke()
    }

    override fun onDetachedFromWindow() {
        onViewInflated = null
        super.onDetachedFromWindow()
    }

    private companion object {
        const val VIEW_NON_INFLATE_SIZE = 0
    }
}
