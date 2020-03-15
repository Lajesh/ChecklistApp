package com.newyorktimes.newsapp.data.remote.model

import com.lavaira.checklistapp.data.remote.model.NewsItem
import org.junit.Test
import org.junit.Assert.*

/****
 * Unit test for News Item
 * Author: Lajesh Dineshkumar
 * Created on: 4/13/19
 * Modified on: 4/13/19
 *****/
class NewsItemTest {
    var newsItem: NewsItem? = null;

    @Test
    fun testObject(){
        assertNull(newsItem)
        newsItem = NewsItem(0, "", "", "", "", ArrayList())
        assertNotNull(newsItem)
    }

    @Test
    fun testAttributes(){
        newsItem = NewsItem(1000L, "Trump has won the election", "Lajesh", "12-01-2019", "", ArrayList())
        assertNotNull(newsItem)
        assertEquals(1000L, newsItem?.id)
        assertEquals("Trump has won the election", newsItem?.title)
        assertEquals("Lajesh", newsItem?.authors)
        assertEquals("12-01-2019", newsItem?.publishedDate)
        assertEquals("", newsItem?.url)
        assertNotNull(newsItem?.media)

        newsItem = NewsItem(0L, null, null, null, null, null)
        assertNotNull(newsItem)
        assertEquals(0L, newsItem?.id)
        assertEquals(null, newsItem?.title)
        assertEquals(null, newsItem?.authors)
        assertEquals(null, newsItem?.publishedDate)
        assertEquals(null, newsItem?.url)
        assertNull(newsItem?.media)
    }
}