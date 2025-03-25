package io.devexpert.accessibilityexpert.classic.exercise1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.databinding.ActivityContentDescriptionBinding

/**
 * Exercise 1: Content Description for ImageViews
 * 
 * Task: Add proper content descriptions to the ImageButtons in the layout
 * file to make them accessible for users with screen readers.
 */
class ContentDescriptionActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityContentDescriptionBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContentDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        // Set up click listeners to demonstrate functionality
        binding.btnHome.setOnClickListener {
            Toast.makeText(this, getString(R.string.home_clicked), Toast.LENGTH_SHORT).show()
        }
        
        binding.btnSettings.setOnClickListener {
            Toast.makeText(this, getString(R.string.settings_clicked), Toast.LENGTH_SHORT).show()
        }
        
        binding.btnProfile.setOnClickListener {
            Toast.makeText(this, getString(R.string.profile_clicked), Toast.LENGTH_SHORT).show()
        }
    }
} 