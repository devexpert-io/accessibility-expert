package io.devexpert.accessibilityexpert

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import io.devexpert.accessibilityexpert.compose.exercise8.CustomViewNavigationScreen
import io.devexpert.accessibilityexpert.ui.theme.AccessibilityExpertTheme
import org.junit.Rule
import org.junit.Test

/**
 * Specific test to verify the accessibility of the custom view in Compose (CustomCircleView).
 */
class ComposeCustomViewAccessibilityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testComposeCustomViewAccessibility() {
        composeTestRule.setContent {
            AccessibilityExpertTheme {
                CustomViewNavigationScreen(onBack = {})
            }
        }

        val context = ApplicationProvider.getApplicationContext<Context>()

        composeTestRule
            .onNodeWithText(context.getString(R.string.click))
            .assertIsDisplayed()
    }

} 