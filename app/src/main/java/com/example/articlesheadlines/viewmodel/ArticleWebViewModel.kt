package com.example.articlesheadlines.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.articlesheadlines.model.Article
import com.example.articlesheadlines.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleWebViewModel(
    app: Application,
    private val repository: NewsRepository
) : AndroidViewModel(app) {
    private val _isSaved = MutableStateFlow(false)
    val isSaved: StateFlow<Boolean> = _isSaved

    fun checkSaved(url: String) {
        viewModelScope.launch {
            _isSaved.value = repository.isArticleSaved(url)
        }
    }

    fun toggleSave(article: Article) {
        viewModelScope.launch {
            if (_isSaved.value) {
                repository.deleteArticle(article)
            } else {
                repository.saveArticle(article)
            }
            _isSaved.value = !_isSaved.value
        }
    }
}