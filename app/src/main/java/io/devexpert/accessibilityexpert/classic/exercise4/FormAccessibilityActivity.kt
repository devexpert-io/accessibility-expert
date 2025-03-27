package io.devexpert.accessibilityexpert.classic.exercise4

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.color.MaterialColors
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.databinding.ActivityFormAccessibilityBinding

/**
 * Exercise 4: Form Accessibility
 *
 * This exercise demonstrates how to create an accessible login form.
 * The form includes text fields for username and password, along with a login button.
 *
 * Tasks:
 * 1. Use standard components with appropriate labels
 * 2. Mark required fields properly
 * 3. Implement error handling with proper error messages
 * 4. Add supporting text to describe errors
 * 5. Allow autocomplete for password fields
 */
class FormAccessibilityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormAccessibilityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFormAccessibilityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up login button click listener
        binding.loginButton.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val email = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        val isValid = email.isNotEmpty() && email.contains("@")
                && password.isNotEmpty() && password.length >= 5

        // Display login result
        binding.loginResult.visibility = View.VISIBLE

        if (isValid) {
            // Success
            binding.loginResult.text = getString(R.string.login_result_success)
            binding.loginResult.setTextColor(
                MaterialColors.getColor(binding.loginResult, android.R.attr.textColorTertiary)
            )
        } else {
            // Failure
            binding.loginResult.text = getString(R.string.login_result_failure)
            binding.loginResult.setTextColor(
                MaterialColors.getColor(binding.loginResult, android.R.attr.colorError)
            )
        }
    }
} 