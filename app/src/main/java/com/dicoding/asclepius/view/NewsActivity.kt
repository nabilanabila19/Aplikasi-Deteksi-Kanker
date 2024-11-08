package com.dicoding.asclepius.view

import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.databinding.ActivityMainBinding
import com.dicoding.asclepius.databinding.ActivityNewsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var tvNotFound: TextView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_news)

        bottomNavigationView = findViewById(R.id.menuBar)
        tvNotFound = findViewById(R.id.tvNotFound)

        bottomNavigationView.selectedItemId = R.id.news
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this,MainActivity::class.java))
                    true
                }

                R.id.news -> {
                    true
                }

                R.id.history -> {
                    startActivity(Intent(this,HistoryActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }
}