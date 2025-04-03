package io.devexpert.accessibilityexpert.compose.exercise6

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.compose.components.BackIconButton
import io.devexpert.accessibilityexpert.compose.preview.MultiPreview
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
object AccessibilityActionsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccessibilityActionsNavigationScreen(onBack: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.exercise6_header),
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
        ItemsList(modifier = Modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(20) { index ->
            SwipeableListItem(
                text = "Element ${index + 1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {}
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableListItem(text: String, modifier: Modifier = Modifier) {
    val state = rememberAnchoredDraggableState()
    var isFavorite by remember { mutableStateOf(false) }
    val likeButtonLabel = getLikeButtonLabel(isFavorite, text)

    Box(
        modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(likeButtonLabel) {
                        isFavorite = !isFavorite
                        true
                    }
                )
            }
    ) {
        LikeButton(
            isFavorite = isFavorite,
            onClick = { isFavorite = !isFavorite },
            contentDescription = likeButtonLabel
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        x = state.requireOffset().roundToInt(),
                        y = 0
                    )
                }
                .background(MaterialTheme.colorScheme.surface)
                .anchoredDraggable(state, Orientation.Horizontal)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BoxScope.LikeButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    contentDescription: String
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .semantics {
                hideFromAccessibility()
            }
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

enum class DragAnchors {
    Center,
    End
}

@Composable
private fun getLikeButtonLabel(isFavorite: Boolean, text: String) =
    if (isFavorite) stringResource(R.string.remove_favorite, text)
    else stringResource(R.string.add_favorite, text)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberAnchoredDraggableState(): AnchoredDraggableState<DragAnchors> {
    val density = LocalDensity.current
    val actionSizePx = with(density) { 48.dp.toPx() }

    return remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Center,
            anchors = DraggableAnchors {
                DragAnchors.Center at 0f
                DragAnchors.End at -actionSizePx
            }
        )
    }
}

@MultiPreview
@Composable
fun AccessibilityActionsScreenPreview() {
    AccessibilityActionsNavigationScreen(onBack = {})
} 