package io.devexpert.accessibilityexpert.classic.exercise8

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import io.devexpert.accessibilityexpert.R

class CustomCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val text = context.getString(R.string.click)

    private val circlePaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 40f
        textAlign = Paint.Align.CENTER
        isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = 200
        val width = resolveSize(size, widthMeasureSpec)
        val height = resolveSize(size, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = width.coerceAtMost(height) / 2f - 10f

        // Draw circle
        canvas.drawCircle(centerX, centerY, radius, circlePaint)

        // Draw text
        canvas.drawText(
            text,
            centerX,
            centerY + textPaint.textSize / 3,
            textPaint
        )
    }
} 