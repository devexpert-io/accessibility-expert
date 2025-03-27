package io.devexpert.accessibilityexpert

import android.app.Application
import androidx.compose.ui.ComposeUiFlags
import androidx.compose.ui.ExperimentalComposeUiApi

class App : Application() {
    
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate() {
        super.onCreate()
        
        ComposeUiFlags.isSemanticAutofillEnabled = true
    }
} 