package com.gemidroid.tempo.base

import android.app.Application
import com.gemidroid.tempo.di.appModule
import com.gemidroid.tempo.di.newsModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        koinStart
        initTimber()
    }

    /**
     * log with timber for debug only
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    /**
     * init service locator with koin to inject dependencies
     */
    private val koinStart = startKoin {
        androidLogger()
        androidContext(this@BaseApp)
        modules(appModule, newsModule)
    }
}
