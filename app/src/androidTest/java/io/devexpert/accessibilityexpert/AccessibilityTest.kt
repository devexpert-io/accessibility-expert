package io.devexpert.accessibilityexpert

import android.view.accessibility.AccessibilityNodeInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.devexpert.accessibilityexpert.classic.exercise1.ContentDescriptionActivity
import org.junit.Rule
import org.junit.Test

/**
 * Example of Espresso tests with accessibility.
 */
class AccessibilityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(ContentDescriptionActivity::class.java)

    init {
        AccessibilityChecks.enable()
    }

    @Test
    fun testContentDescriptions() {
        onView(withId(R.id.btn_home))
            .check(matches(isDisplayed()))
            .check { view, _ ->
                val nodeInfo = AccessibilityNodeInfo()
                view.onInitializeAccessibilityNodeInfo(nodeInfo)
                assert(nodeInfo.contentDescription != null) {
                    "Home button must have a content description"
                }
            }

    }
} 