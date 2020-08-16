package com.gemidroid.tempo.data.datasource.network

import com.gemidroid.tempo.data.model.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/everything")
    fun getAllNews(
        @Query("q") query: String,
        @Query("from") date: String?,
        @Query("sortBy") soringBy: String?,
        @Query("apiKey") apiKey: String
    ): Single<NewsResponse>
}
