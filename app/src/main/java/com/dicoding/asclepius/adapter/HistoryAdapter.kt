package com.dicoding.asclepius.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter(private val onDeleteClickListener: (HistoryEntity) -> Unit) :
    ListAdapter<HistoryEntity, HistoryAdapter.HistoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = getItem(position)
        holder.bind(history)
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.savedImg)
        private val textViewDate: TextView = itemView.findViewById(R.id.tvDate)
        private val textViewLabel: TextView = itemView.findViewById(R.id.tvLabel)
        private val textViewConfidence: TextView = itemView.findViewById(R.id.tvConfidence)
        private val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        fun bind(history: HistoryEntity) {
            Glide.with(itemView.context)
                .load(history.imagePath)
                .into(imageView)

            val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            val formattedDate = dateFormat.format(Date(history.createdAt))
            textViewDate.text = formattedDate

            textViewLabel.text = history.result
            val confidencePercentage = history.confidence * 100
            textViewConfidence.text = "Confidence: ${"%.2f".format(confidencePercentage)}%"

            btnDelete.setOnClickListener {
                onDeleteClickListener(history)
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentList = currentList.toMutableList()
                    currentList.removeAt(position)
                    submitList(currentList)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoryEntity>() {
            override fun areItemsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HistoryEntity, newItem: HistoryEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}