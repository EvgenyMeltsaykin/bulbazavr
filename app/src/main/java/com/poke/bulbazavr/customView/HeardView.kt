package com.poke.bulbazavr.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Path.FillType
import android.util.AttributeSet
import android.view.View
import com.poke.bulbazavr.R
import kotlin.math.cos
import kotlin.math.sin

fun Context.dpToPx(dp: Int): Float {
    return dp.toFloat() * this.resources.displayMetrics.density
}

interface HeardViewController {
    fun setEnable(isEnable: Boolean)
}

class HeardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), HeardViewController {
    companion object {
        private const val DEFAULT_SIZE = 40
    }

    private var isEnable: Boolean = false

    private val paint: Paint = Paint()
    private var radius: Float = (width / 4).toFloat()
    private var centerXLeftCircle: Float = 0f
    private var centerYLeftCircle: Float = 0f
    private var centerXRightCircle: Float = 0f
    private var centerYRightCircle: Float = 0f
    private var distanceX: Float = 0f
    private var distanceY: Float = 0f

    init {
        if (attrs != null) {
            isEnable = context.obtainStyledAttributes(attrs, R.styleable.HeardView)
                .getBoolean(R.styleable.HeardView_hv_enable, false)
        }
        isClickable = true
    }

    override fun performClick(): Boolean {
        super.performClick()
        invalidate()
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = context.dpToPx(DEFAULT_SIZE).toInt()
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        radius = (width / 4).toFloat()
        centerXLeftCircle = (width / 4 + 5).toFloat()
        centerYLeftCircle = (height / 4).toFloat()
        centerXRightCircle = (3 * width / 4 - 5).toFloat()
        centerYRightCircle = (height / 4).toFloat()
        distanceX = sin(45F) * radius
        distanceY = cos(45F) * radius
        if (isEnable) drawEnableHeard(canvas)
        else drawDisableHeard(canvas)
        super.onDraw(canvas)
    }

    private fun drawDisableHeard(canvas: Canvas?) {
        paint.color = Color.GRAY
        paint.style = Paint.Style.FILL
        drawHeard(paint, canvas)
    }

    private fun drawEnableHeard(canvas: Canvas?) {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        drawHeard(paint, canvas)
    }

    private fun drawHeard(paint: Paint, canvas: Canvas?) {
        canvas?.drawCircle(centerXLeftCircle, centerYLeftCircle, radius, paint)
        canvas?.drawCircle(centerXRightCircle, centerYRightCircle, radius, paint)
        var bx = centerXLeftCircle - distanceX
        var by = centerYLeftCircle + distanceY
        val path = Path()
        path.fillType = FillType.EVEN_ODD
        path.moveTo(bx, by)
        path.lineTo(bx, by)
        path.lineTo((width / 2).toFloat(), (7 * height / 8).toFloat())
        bx = centerXRightCircle + distanceX
        by = centerYRightCircle + distanceY
        path.lineTo(bx, by)
        path.close()
        canvas?.drawPath(path, paint)
    }

    override fun setEnable(isEnable: Boolean) {
        this.isEnable = isEnable
        invalidate()
    }
}