package com.example.newsapp.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.domain.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.HeadlinesViewHolder>() {

    private val articles: MutableList<Article> = mutableListOf()

    private var listener: HeadlinesItemClickListener? = null

    inner class HeadlinesViewHolder(val itemBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    fun addData(list: List<Article>) {
        val size = articles.size
        articles.addAll(list)
        notifyItemRangeInserted(size, articles.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HeadlinesViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_article,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {
        holder.itemBinding.article = articles[position]
        holder.itemBinding.root.setOnClickListener {
            listener?.onClick(articles[position])
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun clearData() {
        articles.clear()
    }

    fun addOnItemClickListener(listener: HeadlinesItemClickListener) {
        this.listener = listener
    }
}

interface HeadlinesItemClickListener {
    fun onClick(article: Article)
}