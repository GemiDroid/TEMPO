package com.gemidroid.tempo.base

import io.reactivex.rxjava3.core.Scheduler

interface IExecutors {
    fun getMainThread(): Scheduler
    fun getIOThread(): Scheduler
}
