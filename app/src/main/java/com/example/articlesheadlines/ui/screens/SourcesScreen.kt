package com.example.articlesheadlines.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.articlesheadlines.viewmodel.SourcesViewModel
import com.example.articlesheadlines.model.Source
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SourcesScreen(viewModel: SourcesViewModel = viewModel()) {
    val sources by viewModel.sources.collectAsState()
    val selectedSources by viewModel.selectedSources.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator()
            error != null -> Text(text = error ?: "Unknown error", color = MaterialTheme.colors.error)
            else -> LazyColumn {
                items(sources) { source ->
                    SourceRow(
                        source = source,
                        selected = selectedSources.contains(source.id),
                        onCheckedChange = { checked ->
                            viewModel.setSourceSelected(source.id, checked)
                        }
                    )
                    Divider()
                }
            }
        }
    }
}

@Composable
fun SourceRow(source: Source, selected: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth().padding(12.dp)) {
        Text(source.name, Modifier.weight(1f))
        Checkbox(checked = selected, onCheckedChange = onCheckedChange)
    }
}