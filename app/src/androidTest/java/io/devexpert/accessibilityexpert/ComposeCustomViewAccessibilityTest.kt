package io.devexpert.accessibilityexpert

import android.content.Context
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasRequestFocusAction
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
            .assertHasClickAction()
            .assert(hasRequestFocusAction())
            .assert(
                SemanticsMatcher("has correct role") {
                    it.config.getOrNull(SemanticsProperties.Role) == Role.Button
                },
            )
    }

} 