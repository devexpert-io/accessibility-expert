package io.devexpert.accessibilityexpert.compose.exercises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.compose.components.BackIconButton
import io.devexpert.accessibilityexpert.compose.preview.MultiPreview
import io.devexpert.accessibilityexpert.ui.theme.AccessibilityExpertTheme
import kotlinx.serialization.Serializable

/**
 * Exercise 4: Form Accessibility in Compose
 *
 * This exercise demonstrates how to create an accessible login form.
 * The form includes text fields for email and password, along with a login button.
 *
 * Tasks:
 * 1. Use standard components with appropriate labels
 * 2. Mark required fields properly
 * 3. Implement error handling with proper error messages
 * 4. Add supporting text to describe errors
 * 5. Allow autocomplete for password fields
 */
@Serializable
object FormAccessibilityScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAccessibilityScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.exercise4_title)) },
                navigationIcon = { BackIconButton(onBack) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            FormContent(modifier = Modifier.widthIn(max = 320.dp))
        }
    }
}

@Composable
fun FormContent(
    modifier: Modifier = Modifier
) {
    // Form state
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showLoginResult by remember { mutableStateOf(false) }
    var loginSuccessful by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    val (emailFocusRequester, passwordFocusRequester) = remember { FocusRequester.createRefs() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Description text
        Text(
            text = stringResource(R.string.exercise4_description),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Email field
        val emailSupportingText = emailError ?: stringResource(R.string.required_field)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it; emailError = null },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(emailFocusRequester)
                .semantics { contentType = ContentType.EmailAddress },
            label = { Text(stringResource(R.string.username)) },
            supportingText = { Text(emailSupportingText) },
            isError = emailError != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        // Password field
        val passwordSupportingText = passwordError ?: stringResource(R.string.required_field)

        OutlinedTextField(
            value = password,
            onValueChange = { password = it; passwordError = null },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester)
                .semantics { contentType = ContentType.Password },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(stringResource(R.string.password)) },
            supportingText = { Text(passwordSupportingText) },
            isError = passwordError != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        
        // Login button
        Button(
            onClick = {
                // Clear previous errors
                emailError = null
                passwordError = null
                var isValid = true

                // Validate email
                if (email.isEmpty()) {
                    emailError = context.getString(R.string.username_required_error)
                    isValid = false
                } else if (!email.contains('@')) {
                    emailError = context.getString(R.string.email_invalid)
                    isValid = false
                }

                // Validate password
                if (password.isEmpty()) {
                    passwordError = context.getString(R.string.password_required_error)
                    isValid = false
                } else if (password.length < 5) {
                    passwordError = context.getString(R.string.password_invalid)
                    isValid = false
                }

                // Display login result
                showLoginResult = true
                loginSuccessful = isValid

                // Set focus to first field with error
                when {
                    emailError != null -> emailFocusRequester.requestFocus()
                    passwordError != null -> passwordFocusRequester.requestFocus()
                    else -> focusManager.clearFocus()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.login))
        }

        // Login result text
        if (showLoginResult) {
            Text(
                text = stringResource(
                    if (loginSuccessful) R.string.login_result_success
                    else R.string.login_result_failure
                ),
                color = if (loginSuccessful)
                    MaterialTheme.colorScheme.tertiary
                else
                    MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@MultiPreview
@Composable
fun FormAccessibilityScreenPreview() {
    AccessibilityExpertTheme {
        FormAccessibilityScreen(onBack = {})
    }
}