package com.gemidroid.tempo.presentation.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.gemidroid.tempo.R
import com.gemidroid.tempo.data.model.NewsParams
import com.gemidroid.tempo.presentation.news.viewmodel.NewsViewModel
import com.gemidroid.tempo.util.Constants
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.net.UnknownHostException


class NewsListFragment : Fragment() {

    private val newsViewModel by viewModel<NewsViewModel>()
    private lateinit var query: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel.getAllNews.observe(viewLifecycleOwner, Observer { newsList ->
            if (!newsList.isNullOrEmpty()) {
                toggleList(true)
                rec_news.apply {
                    adapter = NewsListAdapter(newsList) { newsItems ->
                        // Navigate to details screen ...
                        findNavController().navigate(
                            R.id.action_newsListFragment_to_newsDetailsFragment,
                            bundleOf(Constants.NEWS_KEY to newsItems)
                        )
                    }
                }
                Timber.d(newsList.toString())
            } else {
                toggleList(false)
                txt_news_results.text = getString(R.string.no_data)
            }
        })

        newsViewModel.getError.observe(viewLifecycleOwner, Observer {
            toggleList(false)
            if (it is UnknownHostException)
                txt_news_results.text = getString(R.string.no_internet_msg)
            else txt_news_results.text = "${it.localizedMessage}"
        })

        newsViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (!it)
                progress.visibility = View.GONE
        })

        edt_search.addTextChangedListener{text->
                if (text.isNullOrEmpty()) return@addTextChangedListener
                else loadNews(text.trim().toString())
            }
    }

    private fun loadNews(query: String) {
        newsViewModel.getNewsData(
            NewsParams(query = query, fromDate = Constants.DATE, sortBy = Constants.SORT_BY)
        )
    }

    private fun toggleList(isShown: Boolean) {
        if (isShown) {
            rec_news.visibility = View.VISIBLE
            txt_news_results.visibility = View.GONE
        } else {
            rec_news.visibility = View.GONE
            txt_news_results.visibility = View.VISIBLE
        }
    }
}