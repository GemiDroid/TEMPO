package com.gemidroid.tempo.base

interface UseCase<in T, out U> {
    fun execute(value: T): U
    fun flushResources()
}
