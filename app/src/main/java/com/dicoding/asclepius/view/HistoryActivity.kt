package com.dicoding.asclepius.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var tvNotFound: TextView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_history)

        bottomNavigationView = findViewById(R.id.menuBar)
        tvNotFound = findViewById(R.id.tvNotFound)

        bottomNavigationView.selectedItemId = R.id.history
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this,MainActivity::class.java))
                    true
                }

                R.id.news -> {
                    startActivity(Intent(this,NewsActivity::class.java))
                    true
                }

                R.id.history -> {
                    true
                }

                else -> false
            }
        }
    }
}