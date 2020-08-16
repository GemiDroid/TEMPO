package com.gemidroid.tempo.domain.usecases.news

import androidx.lifecycle.MutableLiveData
import com.gemidroid.tempo.base.IExecutors
import com.gemidroid.tempo.base.UseCase
import com.gemidroid.tempo.data.model.News
import com.gemidroid.tempo.data.model.NewsParams
import com.gemidroid.tempo.data.repositories.AllNewsRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class GetAllNewsUseCase(
    private val compositeDisposable: CompositeDisposable,
    private val executors: IExecutors,
    private val getAllNewsRepository: AllNewsRepository
) : UseCase<NewsParams, Unit> {

    override fun execute(value: NewsParams) {
        compositeDisposable.add(getAllNewsRepository.getAllNews(value)
            .doOnSubscribe {
                showProgress.postValue(true)
            }
            .doFinally {
                showProgress.postValue(false)
            }

            .subscribeOn(executors.getIOThread())
            .subscribe({ newsResponse ->
                    getAllNewsList.postValue(newsResponse.addressResponse)
            },
                { error ->
                    onCatchError.postValue(error)
                }
            )
        )
    }

    override fun flushResources() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

    val getAllNewsList = MutableLiveData<List<News>>()
    val onCatchError = MutableLiveData<Throwable>()
    val showProgress = MutableLiveData<Boolean>()
}
