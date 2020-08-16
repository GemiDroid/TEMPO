package com.gemidroid.tempo.di

import com.gemidroid.tempo.base.Executors
import com.gemidroid.tempo.base.IExecutors
import com.gemidroid.tempo.util.Constants
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    factory {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    factory {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory<IExecutors> { Executors() }

    factory { CompositeDisposable() }
}
