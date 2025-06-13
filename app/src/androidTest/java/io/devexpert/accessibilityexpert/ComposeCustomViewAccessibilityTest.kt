package io.devexpert.accessibilityexpert

import android.content.Context
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasRequestFocusAction
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import androidx.test.core.app.ApplicationProvider
import io.devexpert.accessibilityexpert.compose.exercise8.CustomViewNavigationScreen
import io.devexpert.accessibilityexpert.ui.theme.AccessibilityExpertTheme
import org.junit.Test

/**
 * Specific test to verify the accessibility of the custom view in Compose (CustomCircleView).
 */
class ComposeCustomViewAccessibilityTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testComposeCustomViewAccessibility(): Unit = runComposeUiTest {

        enableAccessibilityChecks()

        setContent {
            AccessibilityExpertTheme {
                CustomViewNavigationScreen(onBack = {})
            }
        }

        val context = ApplicationProvider.getApplicationContext<Context>()

        onNodeWithText(context.getString(R.string.click))
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