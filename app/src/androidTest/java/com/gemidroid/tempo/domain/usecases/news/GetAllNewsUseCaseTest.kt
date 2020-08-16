package com.gemidroid.tempo.domain.usecases.news

import com.gemidroid.tempo.data.model.News
import org.junit.Test
import org.junit.Assert.*

class GetAllNewsUseCaseTest  {

    @Test
    fun execute() {

        val expectedList1 = null

        assertNull(expectedList1)

        val expectedList2 = listOf<News>(News(null, author = "author1",
        title = "title1", description = "description1", url="www.google.com",
        img = "", content = "", publishedAt = "15-09-2019"))

        assertNotNull(expectedList2)

    }
}