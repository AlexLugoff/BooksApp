package com.example.booksapp.features.books

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.booksapp.core.model.Book
import com.example.booksapp.features.books.components.BooksContent
import com.example.booksapp.features.books.components.MainAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    val booksViewModel: BooksViewModel = hiltViewModel()
    val state by booksViewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        booksViewModel.effect.collect { effect ->
            when (effect) {
                is BooksUiEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            MainAppBar(
                searchWidgetState = state.searchWidgetState,
                searchTextState = state.searchQuery,
                onTextChange = {
                    booksViewModel.handleIntent(BooksIntent.SearchQueryChanged(it))
                },
                onCloseClicked = {
                    booksViewModel.handleIntent(BooksIntent.CloseSearchWidget)
                },
                onSearchClicked = {
                    booksViewModel.handleIntent(BooksIntent.SearchClicked(it))
                },
                onSearchTriggered = {
                    booksViewModel.handleIntent(BooksIntent.ToggleSearchWidget)
                }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = { booksViewModel.handleIntent(BooksIntent.Refresh) },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BooksContent(
                state = state,
                onRetry = { booksViewModel.handleIntent(BooksIntent.Refresh) },
                onBookClicked = onBookClicked
            )
        }
    }
}
