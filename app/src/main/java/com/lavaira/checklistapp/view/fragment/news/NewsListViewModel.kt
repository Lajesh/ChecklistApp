package com.lavaira.checklistapp.view.fragment.news

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.lavaira.checklistapp.BR
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.architecture.SingleLiveEvent
import com.lavaira.checklistapp.data.remote.model.NewsListResponse
import com.lavaira.checklistapp.data.remote.model.response.ApiResponse
import com.lavaira.checklistapp.data.remote.model.response.ResponseListener
import com.lavaira.checklistapp.listeners.OnItemClickListener
import com.lavaira.checklistapp.repository.NewsRepository
import com.lavaira.checklistapp.viewmodel.BaseViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

/****
 * The presentation layer of News List fragment
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
class NewsListViewModel @Inject constructor(private val newsRepository: NewsRepository) : BaseViewModel(){

    val navigateToDetails =  SingleLiveEvent<String?>()
    val emptyListEvent = SingleLiveEvent<Boolean>()
    var shouldHideRefresh = MutableLiveData<Boolean>()
    val filterText = MutableLiveData<String>()
    private val actualItems = arrayListOf<NewsItemViewModel>()

    private var isSwipeToRefresh: Boolean = false

    /**
     * The Menu Item list.
     */
    val items: ObservableList<NewsItemViewModel> = ObservableArrayList<NewsItemViewModel>()

    /**
     * The Single item.
     */
    val itemBinding: ItemBinding<NewsItemViewModel> = ItemBinding.of<NewsItemViewModel> { itemBinding, position, item ->
        itemBinding.set(BR.itemViewModel, R.layout.item_news)
        itemBinding.bindExtra(BR.itemClickListener, object : OnItemClickListener {
            override fun onItemClick(item: NewsItemViewModel) {
                navigateToDetails.value = item.newsUrl.value
            }
        })
    }

    /**
     * Service call that fetches the list of news
     */
    fun loadNews(){
        items.clear()
        actualItems.clear()
        newsRepository.getNews(1, object : ResponseListener<NewsListResponse> {
            override fun onStart() {
                if(!isSwipeToRefresh)
                    loadingStatus.value = true
            }

            override fun onFinish() {
                loadingStatus.value = false
            }

            override fun onResponse(result: ApiResponse<NewsListResponse>) {
                shouldHideRefresh.value = true

                if(result.error == null && null != result.data){
                    emptyListEvent.value = false
                    prepareNewsList(result.data)
                }else{
                    emptyListEvent.value = true
                    serviceErrorEvent.value = result.errorDescription
                }
            }

        })
    }

    /**
     * Prepares the newslist observable for rendering
     * @param newsListResponse: NewsListResponse
     */
    private fun prepareNewsList(newsListResponse: NewsListResponse) {
        val newsItems = newsListResponse.popularNewsList?.let { it } ?: return
        for (newsItem in newsItems) {
            items.add(
                NewsItemViewModel(newsItem.title, newsItem.authors,
                newsItem.publishedDate, newsItem.url, newsItem.media!![0].mediaMetadata!![0].imageUrl)
            )
        }
        actualItems.addAll(items)
    }


    /**
     * Method to be invoked on Swipe to refresh
     */
    fun swipeRefreshEvent() {
        isSwipeToRefresh = true
        loadNews()
    }


    /**
     * Method to filter the list based on user query
     */
    fun filterNews(query: String) {
        val filterItems = actualItems.filter { result ->
            result.newsTitle.value?.contains(query, ignoreCase = true) ?: true
        }
        items.clear()
        items.addAll(filterItems)
    }

}