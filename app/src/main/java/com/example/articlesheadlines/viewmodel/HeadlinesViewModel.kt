package com.example.articlesheadlines.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.articlesheadlines.model.Article
import com.example.articlesheadlines.repository.NewsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HeadlinesViewModel(
    app: Application,
    private val repository: NewsRepository
) : AndroidViewModel(app) {

    private val _headlines = MutableStateFlow<List<Article>>(emptyList())
    val headlines: StateFlow<List<Article>> = _headlines

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val selectedSources = repository.getSelectedSources()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptySet())

    init {
        observeSourcesAndLoad()
    }

    private fun observeSourcesAndLoad() {
        viewModelScope.launch {
            selectedSources.collect { sources ->
                if (sources.isNotEmpty()) {
                    loadHeadlines(sources)
                } else {
                    _headlines.value = emptyList()
                    _isLoading.value = false
                }
            }
        }
    }

    fun loadHeadlines(sources: Set<String>) {
        _isLoading.value = true
        val srcs = sources.joinToString(",")
        repository.getHeadlines(srcs)
            .subscribe(
                { result ->
                    _headlines.value = result
                    _isLoading.value = false
                    _error.value = null
                },
                { throwable ->
                    _error.value = throwable.message
                    _isLoading.value = false
                }
            )
    }
}