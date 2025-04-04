package io.devexpert.accessibilityexpert.classic.exercise8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.devexpert.accessibilityexpert.databinding.ActivityCustomViewBinding

/**
 * Exercise 8: Custom View Accessibility
 * 
 * Task: Make this custom view accessible by implementing:
 * 1. Content description
 * 2. Clickable and focusable properties
 * 3. Any other accessibility features needed
 * 
 * Use TalkBack to test your implementation.
 */
class CustomViewActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCustomViewBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        // TODO: Set up a click listener for the custom view
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
} 