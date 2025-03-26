package io.devexpert.accessibilityexpert.classic.exercise1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.databinding.ActivityContentDescriptionBinding
import io.devexpert.accessibilityexpert.toast

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
        binding.btnHome.setOnClickListener { toast(R.string.home_clicked) }

        binding.btnSettings.setOnClickListener { toast(R.string.settings_clicked) }

        binding.btnProfile.setOnClickListener { toast(R.string.profile_clicked) }
    }
} 