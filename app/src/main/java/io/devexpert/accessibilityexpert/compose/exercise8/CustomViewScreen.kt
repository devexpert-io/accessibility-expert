package io.devexpert.accessibilityexpert.compose.exercise8

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.compose.components.BackIconButton
import io.devexpert.accessibilityexpert.compose.preview.MultiPreview
import io.devexpert.accessibilityexpert.ui.theme.AccessibilityExpertTheme
import kotlinx.serialization.Serializable

@Serializable
object CustomViewScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomViewNavigationScreen(onBack: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.exercise8_header),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = { BackIconButton(onBack) },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        CustomViewContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun CustomViewContent(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.widthIn(max = 600.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.exercise8_description),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )

            // Custom circle view
            CustomCircleView(modifier = Modifier.size(200.dp))
        }
    }
}

@Composable
fun CustomCircleView(modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = modifier.background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        val textToDraw = stringResource(R.string.click)

        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = (size.minDimension / 2) - 10f

            // Draw circle
            drawCircle(
                color = Color.Blue,
                center = center,
                radius = radius
            )

            val textLayoutResult = textMeasurer.measure(
                text = textToDraw,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            )

            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    center.x - textLayoutResult.size.width / 2,
                    center.y - textLayoutResult.size.height / 2
                ),
                color = Color.White
            )
        }
    }
}

@MultiPreview
@Composable
fun CustomViewScreenPreview() {
    AccessibilityExpertTheme {
        CustomViewNavigationScreen(onBack = {})
    }
} 