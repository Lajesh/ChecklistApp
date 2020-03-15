package com.newyorktimes.newsapp.data.remote.model

import com.lavaira.checklistapp.data.remote.model.NewsItem
import com.lavaira.checklistapp.data.remote.model.NewsListResponse
import org.junit.Test
import org.junit.Assert.*

/****
 * File Description
 * Author: Lajesh Dineshkumar
 * Created on: 4/13/19
 * Modified on: 4/13/19
 *****/
class NewsListResponseTest {
    var newsListResponse: NewsListResponse? = null;

    @Test
    fun testObject(){
        assertNull(newsListResponse)
        newsListResponse = NewsListResponse(ArrayList())
        assertNotNull(newsListResponse)
    }

    @Test
    fun testAttributes(){
        val newsItems = ArrayList<NewsItem>();
        newsItems.add(NewsItem(100L, "Test", "Test", "1-1-2019", "", ArrayList()))
        newsListResponse = NewsListResponse(newsItems)
        assertNotNull(newsListResponse)
        assertEquals(100L, newsListResponse?.popularNewsList?.get(0)?.id)
        assertEquals("Test", newsListResponse?.popularNewsList?.get(0)?.title)
        assertEquals("Test", newsListResponse?.popularNewsList?.get(0)?.authors)
        assertEquals("1-1-2019", newsListResponse?.popularNewsList?.get(0)?.publishedDate)
        assertNotNull(newsListResponse?.popularNewsList?.get(0)?.media)
    }
}