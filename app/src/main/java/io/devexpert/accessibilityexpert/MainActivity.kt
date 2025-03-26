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
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.devexpert.accessibilityexpert.classic.ClassicViewsActivity
import io.devexpert.accessibilityexpert.compose.ComposeActivity
import io.devexpert.accessibilityexpert.ui.theme.AccessibilityExpertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AccessibilityExpertTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AccessibilityDashboard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
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
                .fillMaxWidth(0.7f)
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(R.string.classic_views_button))
        }
        
        Button(
            onClick = {
                context.startActivity(Intent(context, ComposeActivity::class.java))
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 8.dp),
            enabled = true
        ) {
            Text(text = stringResource(R.string.compose_button).replace(" (Coming Soon)", ""))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccessibilityDashboardPreview() {
    AccessibilityExpertTheme {
        AccessibilityDashboard()
    }
}