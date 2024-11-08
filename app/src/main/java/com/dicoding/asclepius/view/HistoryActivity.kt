package com.dicoding.asclepius.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.room.HistoryDatabase
import com.dicoding.asclepius.data.repository.InformationRepository
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var tvNotFound: TextView
    private lateinit var bottomNavigationView: BottomNavigationView

    private val historyViewModel by viewModels<HistoryViewModel> {
        HistoryViewModelFactory(InformationRepository(HistoryDatabase.getInstance(this).historyDao()))
    }
    private val historyAdapter by lazy {
        HistoryAdapter { history ->
            historyViewModel.deleteHistory(history) // Ganti viewModel dengan historyViewModel
        }
    }

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

        historyViewModel.history.observe(this) { historyList ->
            historyAdapter.submitList(historyList)
            if (historyList.isEmpty()) {
                tvNotFound.visibility = View.VISIBLE
            } else {
                tvNotFound.visibility = View.GONE
            }
        }

        binding.rvHistory.adapter = historyAdapter // Pasang adapter ke RecyclerView
        binding.rvHistory.layoutManager = LinearLayoutManager(this) // Atur layout manager
    }
}