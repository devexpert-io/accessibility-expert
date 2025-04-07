package io.devexpert.accessibilityexpert

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
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
    }
} 