package com.gemidroid.tempo.data.repositories

import com.gemidroid.tempo.data.datasource.network.NewsAPI
import com.gemidroid.tempo.data.model.NewsParams
import com.gemidroid.tempo.data.model.NewsResponse
import com.gemidroid.tempo.util.Constants
import io.reactivex.rxjava3.core.Single

class AllNewsRepositoryImpl(private val newsAPI: NewsAPI) : AllNewsRepository {
    override fun getAllNews(newsParams: NewsParams): Single<NewsResponse> =
         newsAPI.getAllNews(
            query = newsParams.query, date = newsParams.fromDate, soringBy = newsParams.sortBy,
            apiKey = Constants.API_KEY)
}