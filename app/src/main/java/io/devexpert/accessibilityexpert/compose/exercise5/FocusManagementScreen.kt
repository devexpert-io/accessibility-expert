package io.devexpert.accessibilityexpert.compose.exercise5

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.compose.preview.MultiPreview
import kotlinx.serialization.Serializable

@Serializable
object FocusManagementScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FocusManagementNavigationScreen(onBack: () -> Unit) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.exercise5_header),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = { MoreActions() },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = { BottomNavigationBar() },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ButtonRow()
            ItemsList()
        }
    }
}

@Composable
private fun MoreActions() {
    var showMenu by remember { mutableStateOf(false) }
    IconButton(onClick = { }) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = stringResource(R.string.action_settings)
        )
    }

    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.more_actions)
        )
    }
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(
            text = { Text(stringResource(R.string.action_help)) },
            onClick = { showMenu = false },
        )
    }
}

@Composable
private fun BottomNavigationBar() {
    var selectedNavItem by remember { mutableIntStateOf(0) }
    NavigationBar {
        NavigationBarItem(
            selected = selectedNavItem == 0,
            onClick = { selectedNavItem = 0 },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(R.string.nav_home)
                )
            },
            label = { Text(stringResource(R.string.nav_home)) }
        )
        NavigationBarItem(
            selected = selectedNavItem == 1,
            onClick = { selectedNavItem = 1 },
            icon = {
                Icon(
                    Icons.Default.Info,
                    contentDescription = stringResource(R.string.nav_dashboard)
                )
            },
            label = { Text(stringResource(R.string.nav_dashboard)) }
        )
        NavigationBarItem(
            selected = selectedNavItem == 2,
            onClick = { selectedNavItem = 2 },
            icon = {
                Icon(
                    Icons.AutoMirrored.Default.Send,
                    contentDescription = stringResource(R.string.nav_notifications)
                )
            },
            label = { Text(stringResource(R.string.nav_notifications)) }
        )
    }
}

@Composable
fun ButtonRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        TextButton(
            onClick = { },
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 4.dp)
        ) {
            Text(stringResource(R.string.button_1))
        }
        TextButton(
            onClick = { },
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 4.dp)
        ) {
            Text(stringResource(R.string.button_2))
        }
        TextButton(
            onClick = { },
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 4.dp)
        ) {
            Text(stringResource(R.string.button_3))
        }
        TextButton(
            onClick = { },
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 4.dp)
        ) {
            Text(stringResource(R.string.button_4))
        }
    }
}

@Composable
fun ItemsList() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(20) { index ->
            Text(
                text = "Elemento $index",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {})
                    .padding(16.dp)
            )
        }
    }
}

@MultiPreview
@Composable
fun FocusManagementScreenPreview() {
    FocusManagementNavigationScreen(onBack = {})
}