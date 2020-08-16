package com.gemidroid.tempo.di

import com.gemidroid.tempo.data.datasource.network.NewsAPI
import com.gemidroid.tempo.data.repositories.AllNewsRepository
import com.gemidroid.tempo.data.repositories.AllNewsRepositoryImpl
import com.gemidroid.tempo.domain.usecases.news.GetAllNewsUseCase
import com.gemidroid.tempo.presentation.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val newsModule = module {
    single { get<Retrofit>().create(NewsAPI::class.java) }
    factory<AllNewsRepository> { AllNewsRepositoryImpl(get()) }
    factory { GetAllNewsUseCase(get(), get(), get()) }
    viewModel { NewsViewModel(get()) }
}
