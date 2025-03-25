package io.devexpert.accessibilityexpert.service

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

/**
 * An accessibility service that logs all accessibility events to Logcat.
 * Useful for debugging and understanding the accessibility events flow in the app.
 */
class LoggingAccessibilityService : AccessibilityService() {

    companion object {
        private const val TAG = "LoggingAccessibility"
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG, "LoggingAccessibilityService connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        
        // Log the event type
        val eventType = getEventTypeString(event.eventType)
        Log.d(TAG, "Event Type: $eventType")
        
        // Log the package name
        Log.d(TAG, "Package: ${event.packageName}")
        
        // Log the event time
        Log.d(TAG, "Event Time: ${event.eventTime}")
        
        // Log the source info if available
        event.source?.let { nodeInfo ->
            logNodeInfo(nodeInfo)
        }
        
        // Log the event text
        if (event.text.isNotEmpty()) {
            Log.d(TAG, "Text: ${event.text}")
        }
        
        // Log the content description if available
        event.contentDescription?.let {
            Log.d(TAG, "Content Description: $it")
        }
        
        // For click events, log more details
        if (event.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            Log.d(TAG, "View Clicked: ${event.className}")
        }
        
        // Add a divider for readability in logs
        Log.d(TAG, "--------------------------------------")
    }

    private fun logNodeInfo(nodeInfo: AccessibilityNodeInfo) {
        Log.d(TAG, "Source Class: ${nodeInfo.className}")
        Log.d(TAG, "Source ViewId: ${nodeInfo.viewIdResourceName}")
        Log.d(TAG, "Source Clickable: ${nodeInfo.isClickable}")
        Log.d(TAG, "Source Focusable: ${nodeInfo.isFocusable}")
        Log.d(TAG, "Source Focused: ${nodeInfo.isFocused}")
        
        nodeInfo.text?.let {
            Log.d(TAG, "Source Text: $it")
        }
        
        nodeInfo.contentDescription?.let {
            Log.d(TAG, "Source Content Description: $it")
        }
    }

    override fun onInterrupt() {
        Log.w(TAG, "LoggingAccessibilityService interrupted")
    }
    
    /**
     * Helper method to convert event type constants to readable strings
     */
    private fun getEventTypeString(eventType: Int): String {
        return when (eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> "TYPE_VIEW_CLICKED"
            AccessibilityEvent.TYPE_VIEW_LONG_CLICKED -> "TYPE_VIEW_LONG_CLICKED"
            AccessibilityEvent.TYPE_VIEW_FOCUSED -> "TYPE_VIEW_FOCUSED"
            AccessibilityEvent.TYPE_VIEW_SELECTED -> "TYPE_VIEW_SELECTED"
            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED -> "TYPE_VIEW_TEXT_CHANGED"
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> "TYPE_WINDOW_STATE_CHANGED"
            AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED -> "TYPE_NOTIFICATION_STATE_CHANGED"
            AccessibilityEvent.TYPE_VIEW_HOVER_ENTER -> "TYPE_VIEW_HOVER_ENTER"
            AccessibilityEvent.TYPE_VIEW_HOVER_EXIT -> "TYPE_VIEW_HOVER_EXIT"
            AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START -> "TYPE_TOUCH_EXPLORATION_GESTURE_START"
            AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END -> "TYPE_TOUCH_EXPLORATION_GESTURE_END"
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> "TYPE_WINDOW_CONTENT_CHANGED"
            AccessibilityEvent.TYPE_VIEW_SCROLLED -> "TYPE_VIEW_SCROLLED"
            AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED -> "TYPE_VIEW_TEXT_SELECTION_CHANGED"
            AccessibilityEvent.TYPE_ANNOUNCEMENT -> "TYPE_ANNOUNCEMENT"
            AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED -> "TYPE_VIEW_ACCESSIBILITY_FOCUSED"
            AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED -> "TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED"
            AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY -> "TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY"
            AccessibilityEvent.TYPE_GESTURE_DETECTION_START -> "TYPE_GESTURE_DETECTION_START"
            AccessibilityEvent.TYPE_GESTURE_DETECTION_END -> "TYPE_GESTURE_DETECTION_END"
            AccessibilityEvent.TYPE_TOUCH_INTERACTION_START -> "TYPE_TOUCH_INTERACTION_START"
            AccessibilityEvent.TYPE_TOUCH_INTERACTION_END -> "TYPE_TOUCH_INTERACTION_END"
            AccessibilityEvent.TYPE_WINDOWS_CHANGED -> "TYPE_WINDOWS_CHANGED"
            AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED -> "TYPE_VIEW_CONTEXT_CLICKED"
            AccessibilityEvent.TYPE_ASSIST_READING_CONTEXT -> "TYPE_ASSIST_READING_CONTEXT"
            else -> "TYPE_UNKNOWN"
        }
    }
} 