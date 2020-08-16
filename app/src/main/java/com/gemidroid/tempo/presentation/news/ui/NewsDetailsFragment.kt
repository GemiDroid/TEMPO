package com.gemidroid.tempo.presentation.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.gemidroid.tempo.R
import com.gemidroid.tempo.base.dateTimeFormatter
import com.gemidroid.tempo.data.model.News
import com.gemidroid.tempo.util.Constants
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getSerializable(Constants.NEWS_KEY) as News).let {
            fillNewsDetails(it)
        }

        img_back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun fillNewsDetails(news: News) {
        txt_news_date.text = dateTimeFormatter(news.publishedAt)
        txt_news_title.text = "title:  ${news.title}"
        txt_news_author.text = "Author: ${news.author}"
        txt_news_source.text ="Source: ${news.source?.name}"
        txt_news_link.text = "Link: ${news.url}"
        txt_news_content.text ="Content: ${ news.content}"
        txt_news_description.text = "Description: ${ news.description}"
        Glide.with(requireContext()).load(news.img).
            error(R.drawable.ic_launcher_background)
            .into(img_news_details)
    }
}
