package io.devexpert.accessibilityexpert.classic.exercise7

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.databinding.ActivityLiveRegionsBinding

/**
 * Activity for demonstrating Live Regions in Android.
 * Live Regions are used to notify screen readers about dynamic content changes.
 */
class LiveRegionsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLiveRegionsBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLiveRegionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        setupButton()
    }
    
    private fun setupButton() {
        binding.actionButton.setOnClickListener {
            binding.resultText.text = getString(R.string.button_pressed)
            binding.resultText.visibility = View.VISIBLE
        }
    }
} 