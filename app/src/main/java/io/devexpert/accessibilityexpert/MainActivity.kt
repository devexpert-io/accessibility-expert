package io.devexpert.accessibilityexpert

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.devexpert.accessibilityexpert.classic.ClassicViewsActivity
import io.devexpert.accessibilityexpert.compose.ComposeActivity
import io.devexpert.accessibilityexpert.compose.preview.MultiPreview
import io.devexpert.accessibilityexpert.ui.theme.AccessibilityExpertTheme
import io.devexpert.accessibilityexpert.classic.exercise5.FocusManagementActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AccessibilityExpertTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        AccessibilityDashboard(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AccessibilityDashboard(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.main_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = stringResource(R.string.choose_platform),
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                context.startActivity(Intent(context, ClassicViewsActivity::class.java))
            },
            modifier = Modifier
                .widthIn(max = 320.dp)
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(R.string.classic_views_button))
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, ComposeActivity::class.java))
            },
            modifier = Modifier
                .widthIn(max = 320.dp)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            enabled = true
        ) {
            Text(text = stringResource(R.string.compose_button).replace(" (Coming Soon)", ""))
        }
    }
}

@MultiPreview
@Composable
fun AccessibilityDashboardPreview() {
    AccessibilityExpertTheme {
        HomeScreen()
    }
}