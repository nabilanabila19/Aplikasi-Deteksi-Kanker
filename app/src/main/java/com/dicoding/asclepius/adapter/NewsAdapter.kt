package com.dicoding.asclepius.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.model.News

class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItemPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvItemTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvItemDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val btnViewMore: Button = itemView.findViewById(R.id.btn_view_more)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)

        Glide.with(holder.itemView.context)
            .load(news.imageUrl)
            .into(holder.imgItemPhoto)

        holder.tvItemTitle.text = news.title
        holder.tvItemDescription.text = news.description

        holder.btnViewMore.setOnClickListener {
            val url = news.url
            val customTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.launchUrl(holder.itemView.context, Uri.parse(url))
        }
    }

//    override fun getItemCount(): Int {
//        return newsList.size
//    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }
}