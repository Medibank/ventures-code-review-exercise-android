package com.example.articlesheadlines.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.articlesheadlines.model.Source
import com.example.articlesheadlines.repository.NewsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SourcesViewModel(
    app: Application,
    private val repository: NewsRepository
) : AndroidViewModel(app) {

    private val _sources = MutableStateFlow<List<Source>>(emptyList())
    val sources: StateFlow<List<Source>> = _sources

    val selectedSources = repository.getSelectedSources()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptySet())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadSources()
    }

    fun loadSources() {
        _isLoading.value = true
        repository.getSources()
            .subscribe(
                { result ->
                    _sources.value = result
                    _isLoading.value = false
                },
                { throwable ->
                    _error.value = throwable.message
                    _isLoading.value = false
                }
            )
    }

    fun setSourceSelected(sourceId: String, selected: Boolean) {
        viewModelScope.launch {
            val current = selectedSources.value.toMutableSet()
            if (selected) current.add(sourceId) else current.remove(sourceId)
            repository.setSelectedSources(current)
        }
    }
}