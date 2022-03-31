package com.hamza.newsapp.ui.Fragments.HomeFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hamza.newsapp.R
import com.hamza.newsapp.data.Model.Article

class HomeAdapter(private val mList: ArrayList<Article>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = mList[position]

        holder.newTitle.text = currentItem.title
        holder.newsAuthor.text = currentItem.author
        //holder.newsImage.setImageResource(currentItem.urlToImage)
        holder.newsDate.text = currentItem.publishedAt


    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val newsImage: ImageView = itemView.findViewById(R.id.newsImage)
        val newTitle: TextView = itemView.findViewById(R.id.newsTitle)
        val newsAuthor: TextView = itemView.findViewById(R.id.newsAuthor)
        val newsDate: TextView = itemView.findViewById(R.id.newsDate)


    }
}