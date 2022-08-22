package com.hamza.newsapp.ui.Fragments.FaouriteFragment

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hamza.newsapp.data.Model.Article
import com.hamza.newsapp.databinding.NewsItemBinding
import com.hamza.newsapp.util.Util.dateFormat
import com.hamza.newsapp.util.loadImage
import javax.inject.Inject

class FavArticlesAdapter @Inject constructor() :
    RecyclerView.Adapter<FavArticlesAdapter.NewsViewHolder>() {

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

    private var onItemClickListener: ((Article) -> Unit)? = null


    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.itemView.apply {
            holder.binding.newsImage.loadImage(currentItem.urlToImage)
            holder.binding.newsTitle.text = currentItem.title
            holder.binding.newsAuthor.text = "By : ${currentItem.author}"
            holder.binding.newsDate.text = "Publish at ${dateFormat(currentItem.publishedAt)}"

            setOnClickListener {
                onItemClickListener?.let { it(currentItem) }
            }

        }

    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount() = differ.currentList.size

}