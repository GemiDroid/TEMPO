package com.gemidroid.tempo.presentation.news.viewmodel

import androidx.lifecycle.ViewModel
import com.gemidroid.tempo.data.model.NewsParams
import com.gemidroid.tempo.domain.usecases.news.GetAllNewsUseCase
import com.gemidroid.tempo.util.Constants

class NewsViewModel(
    private val getAllNewsUseCase: GetAllNewsUseCase
) : ViewModel() {


    init { getNewsData(NewsParams(query = "null", fromDate = Constants.DATE, sortBy = Constants.SORT_BY )) }

    fun getNewsData(newsParams: NewsParams) { getAllNewsUseCase.execute(newsParams) }

    val getAllNews = getAllNewsUseCase.getAllNewsList
    val getError = getAllNewsUseCase.onCatchError
    val showProgress = getAllNewsUseCase.showProgress
}
