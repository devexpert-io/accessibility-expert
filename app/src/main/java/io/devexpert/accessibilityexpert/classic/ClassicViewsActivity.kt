package io.devexpert.accessibilityexpert.classic

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.devexpert.accessibilityexpert.classic.exercise1.ContentDescriptionActivity
import io.devexpert.accessibilityexpert.classic.exercise2.ColorContrastActivity
import io.devexpert.accessibilityexpert.classic.exercise4.FormAccessibilityActivity
import io.devexpert.accessibilityexpert.databinding.ActivityClassicViewsBinding

/**
 * Main activity for Classic Views exercises.
 * This activity lists all the exercises related to accessibility in classic views.
 */
class ClassicViewsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityClassicViewsBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityClassicViewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        // Initialize exercise button click listeners
        initExerciseButtons()
    }
    
    private fun initExerciseButtons() {
        // Exercise 1: Content Description
        binding.btnExercise1.setOnClickListener {
            startActivity(Intent(this, ContentDescriptionActivity::class.java))
        }
        
        // Exercise 2: Color Contrast
        binding.btnExercise2.setOnClickListener {
            startActivity(Intent(this, ColorContrastActivity::class.java))
        }
        
        // Exercise 4: Form Accessibility
        binding.btnExercise4.setOnClickListener {
            startActivity(Intent(this, FormAccessibilityActivity::class.java))
        }
        
        // More exercise buttons will be initialized here
    }
} 