package io.devexpert.accessibilityexpert.compose.exercises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.compose.components.BackIconButton
import io.devexpert.accessibilityexpert.compose.preview.MultiPreview
import io.devexpert.accessibilityexpert.ui.theme.AccessibilityExpertTheme
import kotlinx.serialization.Serializable

@Serializable
object FavoriteListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteListScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.exercise_favorite_list_title)) },
                navigationIcon = { BackIconButton(onBack) }
            )
        }
    ) { paddingValues ->
        FavoriteListContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
fun FavoriteListContent(
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    // Sample list of items
    val items = remember(ctx) {
        List(20) { index ->
            FavoriteItem(
                id = index,
                title = ctx.getString(R.string.favorite_item_title, index + 1),
                description = ctx.getString(R.string.favorite_item_description, index + 1)
            )
        }
    }

    // Track favorite state for each item
    val favoriteStates = remember {
        mutableStateMapOf<Int, Boolean>().apply {
            items.forEach { item ->
                this[item.id] = false
            }
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.widthIn(max = 600.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.exercise_favorite_list_description),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            items(items) { item ->
                FavoriteItemCard(
                    item = item,
                    isFavorite = favoriteStates[item.id] == true,
                    onToggleFavorite = {
                        favoriteStates[item.id] = favoriteStates[item.id] != true
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteItemCard(
    item: FavoriteItem,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            val favoriteContentDescription = stringResource(R.string.favorite)

            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = favoriteContentDescription,
                    tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

data class FavoriteItem(
    val id: Int,
    val title: String,
    val description: String
)

@MultiPreview
@Composable
fun FavoriteListScreenPreview() {
    AccessibilityExpertTheme {
        FavoriteListScreen(
            onBack = {}
        )
    }
}