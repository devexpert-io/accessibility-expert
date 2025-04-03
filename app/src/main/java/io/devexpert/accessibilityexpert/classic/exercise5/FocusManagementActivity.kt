package io.devexpert.accessibilityexpert.classic.exercise5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.devexpert.accessibilityexpert.R
import io.devexpert.accessibilityexpert.databinding.ActivityFocusManagementBinding

class FocusManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFocusManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFocusManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = SimpleAdapter((1..20).map { "Element $it" })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}

class SimpleAdapter(private val items: List<String>) :
    RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        textView.text = items[position]
        textView.setOnClickListener {
            Toast
                .makeText(textView.context, "Clicked: ${items[position]}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getItemCount() = items.size
}