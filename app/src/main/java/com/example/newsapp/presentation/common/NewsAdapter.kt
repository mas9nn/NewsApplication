package com.example.newsapp.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.domain.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.HeadlinesViewHolder>() {

    private var articles: MutableList<Article> = mutableListOf()

    private var listener: HeadlinesItemClickListener? = null

    inner class HeadlinesViewHolder(val itemBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    fun addData(list: List<Article>) {
//        val oldList = articles
//        val newList = articles.apply {
//            addAll(list)
//        }
//        val diffResult = DiffUtil.calculateDiff(BoxSettingsDiffCallback(oldList, newList))
//        articles = newList
//        diffResult.dispatchUpdatesTo(this)
        val size = articles.size
        articles.addAll(list)
        notifyItemRangeInserted(size, articles.size)
    }

    fun setData(list: List<Article>) {
        articles.clear()
        articles.addAll(list)
        notifyDataSetChanged()
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
        holder.itemBinding.article = articles[holder.adapterPosition]
        holder.itemBinding.listener = listener
        holder.itemBinding.index = holder.adapterPosition
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

    fun updateNews(index: Int) {
        articles[index] = articles[index].apply {
            saved = !saved
        }
        notifyItemChanged(index)
    }

    fun deleteItem(index: Int) {
        articles.removeAt(index)
        notifyItemRemoved(index)
    }
}

class BoxSettingsDiffCallback(
    private val oldList: List<Article>,
    private val newList: List<Article>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url == newList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

interface HeadlinesItemClickListener {
    fun onClick(article: Article)
    fun onBookmarkClick(article: Article, index: Int)
}