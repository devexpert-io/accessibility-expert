package io.devexpert.accessibilityexpert.classic.exercise8

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
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

    // Add a Paint for drawing focus indicator
    private val focusPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 8f
        isAntiAlias = true
    }

    // Track focus state
    private var isFocused = false
    
    init {
        // Make the view clickable and focusable
        isClickable = true
        isFocusable = true
        contentDescription = context.getString(R.string.click)
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

        // Draw focus indicator when view is focused
        if (isFocused) {
            canvas.drawCircle(centerX, centerY, radius + 5f, focusPaint)
        }

        // Draw text
        canvas.drawText(
            text,
            centerX,
            centerY + textPaint.textSize / 3,
            textPaint
        )
    }
    
    // Override onFocusChanged to track focus state
    override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
        isFocused = gainFocus
        invalidate() // Redraw the view to show/hide the focus indicator
    }
    
    // Initialize accessibility
    override fun onInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(info)
        // Make it behave like a button
        info.className = android.widget.Button::class.java.name
        info.isClickable = true
    }
    
    override fun onInitializeAccessibilityEvent(event: AccessibilityEvent) {
        super.onInitializeAccessibilityEvent(event)
        event.className = android.widget.Button::class.java.name
    }
} 