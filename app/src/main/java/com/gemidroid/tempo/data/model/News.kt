package com.gemidroid.tempo.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(
    @SerializedName("source")
    val source: Source? = null,
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val img: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("publishedAt")
    val publishedAt: String
) : Serializable

data class Source(
    @SerializedName("id")
    val Id: String? = null,
    @SerializedName("name")
    val name: String
) : Serializable

data class NewsResponse(
    @SerializedName("articles")
    val addressResponse: MutableList<News>
) : Serializable
