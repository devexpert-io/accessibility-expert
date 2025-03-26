package io.devexpert.accessibilityexpert.classic.exercise2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.devexpert.accessibilityexpert.databinding.ActivityColorContrastBinding

/**
 * Exercise 2: Color Contrast
 *
 * Task: Observe how the low contrast between background (primary) and text (secondary)
 * colors makes the text difficult to read. Accessibility tools can detect this issue.
 * 
 * The solution would be to use colors with sufficient contrast ratio (at least 4.5:1
 * for normal text, and 3:1 for large text).
 */
class ColorContrastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColorContrastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityColorContrastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
} 