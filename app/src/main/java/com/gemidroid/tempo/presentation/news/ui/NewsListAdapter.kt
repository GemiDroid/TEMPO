package com.gemidroid.tempo.presentation.news.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gemidroid.tempo.R
import com.gemidroid.tempo.data.model.News
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListAdapter(
    private val newsList: List<News>,
    private val onItemClick: (News) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemRow = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.news_item, parent, false)

        return object : RecyclerView.ViewHolder(itemRow) {}
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val newsItem = newsList[position]
        // Handle onClick ....
        holder.itemView.apply {
            setOnClickListener {
                onItemClick.invoke(newsItem)
            }
            bind(this, newsItem)
        }
    }

    private fun bind(view: View, newsItem: News) {
        Glide.with(view).load(newsItem.img)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.img_news)
        view.txt_news_title.text = newsItem.title
        view.txt_news_source.text = newsItem.source?.name


    }
}

