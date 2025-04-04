package io.devexpert.accessibilityexpert.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.compose.components.BackIconButton
import io.devexpert.accessibilityexpert.compose.exercises.ColorContrastScreen
import io.devexpert.accessibilityexpert.compose.exercises.ContentDescriptionScreen
import io.devexpert.accessibilityexpert.compose.exercises.FavoriteListScreen
import io.devexpert.accessibilityexpert.compose.exercises.FormAccessibilityScreen
import io.devexpert.accessibilityexpert.compose.exercise5.FocusManagementScreen
import io.devexpert.accessibilityexpert.compose.exercise6.AccessibilityActionsScreen
import io.devexpert.accessibilityexpert.compose.exercise7.LiveRegionsScreen
import io.devexpert.accessibilityexpert.compose.preview.MultiPreview
import io.devexpert.accessibilityexpert.ui.theme.AccessibilityExpertTheme
import kotlinx.serialization.Serializable

@Serializable
object ExerciseListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(
    onExerciseClick: (ExerciseItem) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.compose_views_title)) },
                navigationIcon = { BackIconButton(onBack) }
            )
        }
    ) { paddingValues ->
        ComposeExerciseList(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            onExerciseClick = onExerciseClick
        )
    }
}

/**
 * List of accessibility exercises in Compose
 */
@Composable
fun ComposeExerciseList(
    modifier: Modifier = Modifier,
    onExerciseClick: (ExerciseItem) -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        getExerciseItems().forEach { exercise ->
            Button(
                onClick = { onExerciseClick(exercise) },
                modifier = Modifier
                    .widthIn(max = 320.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = exercise.titleRes))
            }
        }
    }
}

/**
 * Model class for an exercise item
 */
data class ExerciseItem(
    val titleRes: Int,
    val route: Any
)

/**
 * List of available exercises
 */
private fun getExerciseItems(): List<ExerciseItem> {
    return listOf(
        ExerciseItem(
            titleRes = R.string.exercise1_title,
            route = ContentDescriptionScreen
        ),
        ExerciseItem(
            titleRes = R.string.exercise2_title,
            route = ColorContrastScreen
        ),
        ExerciseItem(
            titleRes = R.string.exercise_favorite_list_title,
            route = FavoriteListScreen
        ),
        ExerciseItem(
            titleRes = R.string.exercise4_title,
            route = FormAccessibilityScreen
        ),
        ExerciseItem(
            titleRes = R.string.exercise5_title,
            route = FocusManagementScreen
        ),
        ExerciseItem(
            titleRes = R.string.exercise6_title,
            route = AccessibilityActionsScreen
        ),
        ExerciseItem(
            titleRes = R.string.exercise7_title,
            route = LiveRegionsScreen
        )
        // More exercises will be added here
    )
}

@MultiPreview
@Composable
fun ExerciseListScreenPreview() {
    AccessibilityExpertTheme {
        ExerciseListScreen(
            onExerciseClick = {},
            onBack = {}
        )
    }
} 