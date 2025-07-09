package com.example.articlesheadlines.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

object UserPreferences {
    private val SOURCES_KEY = stringSetPreferencesKey("selected_sources")

    fun getSources(context: Context): Flow<Set<String>> =
        context.dataStore.data.map { prefs -> prefs[SOURCES_KEY] ?: emptySet() }

    suspend fun setSources(context: Context, sources: Set<String>) {
        context.dataStore.edit { prefs -> prefs[SOURCES_KEY] = sources }
    }
}