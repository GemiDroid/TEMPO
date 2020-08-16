package com.gemidroid.tempo.data.model

data class NewsParams(
    val query: String,
    val fromDate: String? = null,
    val sortBy: String? = null
)