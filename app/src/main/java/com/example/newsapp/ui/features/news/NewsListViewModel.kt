package com.example.newsapp.ui.features.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.Constants
import com.example.newsapp.common.Resource
import com.example.newsapp.domain.model.news.Article
import com.example.newsapp.domain.usecase.GetNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {

    private val customJob = Job()
    private val customScope = CoroutineScope(Dispatchers.Main + customJob)

    private val _news = MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val news: StateFlow<Resource<List<Article>>> = _news

    private val _country = MutableStateFlow("us")
    val country: StateFlow<String> = _country

    fun setCountry(country: String) {
        _country.value = country
        getNews()
    }

    fun getNews() {
        customScope.launch {
             getNewsListUseCase(Constants.API_NEWS_TOKEN, _country.value)
                .collect { resource ->
                    _news.value = resource
                }
        }
    }
}