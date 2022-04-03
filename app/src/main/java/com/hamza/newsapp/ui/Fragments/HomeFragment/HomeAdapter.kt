package com.hamza.newsapp.ui.Fragments.HomeFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hamza.newsapp.data.Model.Article
import com.hamza.newsapp.databinding.NewsItemBinding

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    var differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = NewsItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(currentItem.urlToImage).into(holder.binding.newsImage)
            holder.binding.newsTitle.text = currentItem.title
            holder.binding.newsAuthor.text = "author : ${currentItem.author}"
            holder.binding.newsDate.text = "Publish at ${currentItem.publishedAt}"
        }

    }

    override fun getItemCount() = differ.currentList.size

}