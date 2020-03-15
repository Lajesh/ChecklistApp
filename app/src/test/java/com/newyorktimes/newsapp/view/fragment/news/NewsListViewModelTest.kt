package com.newyorktimes.newsapp.view.fragment.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.newyorktimes.newsapp.R
import com.newyorktimes.newsapp.base.BaseUnitTest
import com.lavaira.checklistapp.di.modules.AppModule
import com.lavaira.checklistapp.repository.NewsRepository
import com.lavaira.checklistapp.schedulers.SchedulerProvider
import com.newyorktimes.newsapp.utils.MockResponse.createMockResponse
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import java.net.HttpURLConnection

/****
 * Unit test for NewsList View Model
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2019-12-19
 * Modified on: 2019-12-19
 *****/
@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnit4::class)
class NewsListViewModelTest : BaseUnitTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var newslistViewModel: NewsListViewModel
    private lateinit var newsRepository: NewsRepository
    private lateinit var appModule: AppModule

    @Before
    override fun setUp() {
        super.setUp()
        appModule = AppModule()
        newsRepository = appModule.provideUserkRepository(appModule.provideApi(retrofit), object :
            SchedulerProvider() {
            override fun io(): Scheduler {
                return Schedulers.trampoline()
            }
        })
        newslistViewModel = NewsListViewModel(newsRepository)
    }

    @Test
    fun assertObjects(){
        Assert.assertNotNull(appModule)
        Assert.assertNotNull(newsRepository)
        Assert.assertNotNull(newslistViewModel)
    }

    @Test
    fun testNewsFetchSuccess(){
        mockWebServer.enqueue(createMockResponse("news_response", HttpURLConnection.HTTP_OK))
        newslistViewModel.loadNews()
        Assert.assertNotNull(newslistViewModel.items)
        Assert.assertTrue(newslistViewModel.items.isNotEmpty())
    }

    @Test
    fun testNewsFetchFailure(){
        mockWebServer.enqueue(createMockResponse("news_response", HttpURLConnection.HTTP_BAD_GATEWAY))
        `when`(context.getString(R.string.unknownError)).thenReturn("Unknown Error")
        newslistViewModel.loadNews()
        Assert.assertFalse(newslistViewModel.items.isNotEmpty())
        Assert.assertTrue(newslistViewModel.emptyListEvent.value!!)
        Assert.assertEquals("Unknown Error", newslistViewModel.serviceErrorEvent.value)
    }

    @Test
    fun testSwipeToRefresh(){
        mockWebServer.enqueue(createMockResponse("news_response", HttpURLConnection.HTTP_OK))
        newslistViewModel.swipeRefreshEvent()
        Assert.assertNotNull(newslistViewModel.items)
        Assert.assertTrue(newslistViewModel.items.isNotEmpty())
    }


    @Test
    fun testFilter(){
        mockWebServer.enqueue(createMockResponse("news_response", HttpURLConnection.HTTP_OK))
        newslistViewModel.swipeRefreshEvent()
        newslistViewModel.filterNews("Trump")
        Assert.assertNotNull(newslistViewModel.items)
        Assert.assertTrue(newslistViewModel.items.isNotEmpty())
    }
}