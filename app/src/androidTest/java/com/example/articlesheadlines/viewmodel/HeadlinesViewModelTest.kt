package com.example.articlesheadlines.viewmodel

import com.example.articlesheadlines.model.Article
import com.example.articlesheadlines.repository.NewsRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class HeadlinesViewModelTest {

    private val repo = mock(NewsRepository::class.java)
    private lateinit var vm: HeadlinesViewModel

    @Before
    fun setup() {
        `when`(repo.getHeadlines(anyString())).thenReturn(Single.just(listOf<Article>()))
        vm = HeadlinesViewModel(repo)
    }

    @Test
    fun testLoadHeadlines() = runTest {
        vm.loadHeadlines()
        // Assert stateFlow values as needed
    }
}