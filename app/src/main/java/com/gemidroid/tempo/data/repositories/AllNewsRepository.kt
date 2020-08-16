package com.gemidroid.tempo.data.repositories

import com.gemidroid.tempo.data.model.NewsParams
import com.gemidroid.tempo.data.model.NewsResponse
import io.reactivex.rxjava3.core.Single

interface AllNewsRepository {
    fun getAllNews(
       newsParams: NewsParams
    ): Single<NewsResponse>
}
