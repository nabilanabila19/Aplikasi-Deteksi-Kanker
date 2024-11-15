package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.data.local.room.HistoryDatabase
import com.dicoding.asclepius.data.repository.InformationRepository
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    private val historyViewModel by viewModels<HistoryViewModel> {
        HistoryViewModelFactory(InformationRepository(HistoryDatabase.getInstance(this).historyDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        imageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.resultImage.setImageURI(it)
        }

        val resultText = intent.getStringExtra(EXTRA_RESULT)?.let { it } ?: "Unknown"
        val confidenceScore = intent.getFloatExtra(EXTRA_CONFIDENCE, 0.0f)
        Log.d("ResultActivity", "Prediction: $resultText, Confidence: $confidenceScore")

        binding.resultText.text = "Prediction: $resultText\nConfidence: ${"%.2f".format(confidenceScore * 100)}%"

        binding.saveButton.setOnClickListener {
            val imageUriString = intent.getStringExtra(EXTRA_IMAGE_URI) ?: ""
            val resultText = intent.getStringExtra(EXTRA_RESULT) ?: "Unknown"
            val confidenceScore = intent.getFloatExtra(EXTRA_CONFIDENCE, 0.0f)

            val historyEntity = HistoryEntity(
                imagePath = imageUriString,
                result = resultText,
                confidence = confidenceScore
            )

            historyViewModel.insertHistory(historyEntity)
            Toast.makeText(this, "History saved", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_CONFIDENCE = "extra_confidence"
    }
}