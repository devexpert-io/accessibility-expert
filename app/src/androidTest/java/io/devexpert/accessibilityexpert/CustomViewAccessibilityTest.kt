package io.devexpert.accessibilityexpert

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isFocusable
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.devexpert.accessibilityexpert.classic.exercise8.CustomViewActivity
import org.junit.Rule
import org.junit.Test

/**
 * Specific test to verify the accessibility of the custom view (CustomCircleView).
 */
class CustomViewAccessibilityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CustomViewActivity::class.java)

    init {
        AccessibilityChecks.enable()
    }

    @Test
    fun testCustomViewAccessibilityProperties() {
        onView(withId(R.id.custom_view))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(isFocusable()))
            .check(matches(withContentDescription(R.string.click)))
            .check { view, _ ->
                val nodeInfo = AccessibilityNodeInfoCompat.obtain()
                view.onInitializeAccessibilityNodeInfo(nodeInfo.unwrap())

                assert(nodeInfo.className == android.widget.Button::class.java.name) {
                    "CustomView must behave like a button"
                }
            }
    }
} 