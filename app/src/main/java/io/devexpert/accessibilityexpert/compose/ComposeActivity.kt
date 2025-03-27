package io.devexpert.accessibilityexpert.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.devexpert.accessibilityexpert.compose.exercises.ColorContrastScreen
import io.devexpert.accessibilityexpert.compose.exercises.ContentDescriptionScreen
import io.devexpert.accessibilityexpert.compose.exercises.FavoriteListScreen
import io.devexpert.accessibilityexpert.compose.exercises.FormAccessibilityScreen
import io.devexpert.accessibilityexpert.ui.theme.AccessibilityExpertTheme

/**
 * Main activity for Compose exercises.
 * This activity uses Compose Navigation to navigate between different accessibility examples.
 */
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AccessibilityExpertTheme {
                AppNavigation(onBack = { finish() })
            }
        }
    }
}

/**
 * Main navigation component that sets up the navigation between screens
 */
@Composable
fun AppNavigation(onBack: () -> Unit) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ExerciseListScreen
    ) {
        composable<ExerciseListScreen> {
            ExerciseListScreen(
                onExerciseClick = { navController.navigate(it.route) },
                onBack = onBack
            )
        }

        composable<ContentDescriptionScreen> {
            ContentDescriptionScreen(
                onBack = { navController.navigateUp() }
            )
        }
        
        composable<ColorContrastScreen> {
            ColorContrastScreen(
                onBack = { navController.navigateUp() }
            )
        }
        
        composable<FavoriteListScreen> {
            FavoriteListScreen(
                onBack = { navController.navigateUp() }
            )
        }
        
        composable<FormAccessibilityScreen> {
            FormAccessibilityScreen(
                onBack = { navController.navigateUp() }
            )
        }
    }
}