package com.example.booksapp.features.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.booksapp.R
import com.example.booksapp.core.ui.components.BooksTopAppBar
import com.example.booksapp.core.ui.components.ErrorScreen
import com.example.booksapp.core.ui.components.LoadingState
import com.example.booksapp.core.utils.getShareText
import com.example.booksapp.core.utils.shareText
import com.example.booksapp.features.details.components.BookDetailsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    // Handle side effects (Snackbar)
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is BookDetailsUiEffect.ShowSnackbar -> {
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
            BooksTopAppBar(
                title = uiState.book?.title ?: "",
                onBackClick = onBackClick,
                actions = {
                    uiState.book?.let { book ->
                        // Share Button
                        IconButton(onClick = {
                            context.shareText(book.getShareText())
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = stringResource(R.string.share_icon_text)
                            )
                        }

                        // Open in Browser Button
                        book.previewLink?.let { link ->
                            IconButton(onClick = { uriHandler.openUri(link) }) {
                                Icon(
                                    imageVector = Icons.Default.OpenInBrowser,
                                    contentDescription = stringResource(R.string.open_in_browser)
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.refresh() },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.book != null -> {
                    BookDetailsContent(
                        book = uiState.book!!,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                uiState.error != null && !uiState.isLoading -> {
                    ErrorScreen(
                        message = uiState.error,
                        onRetry = { viewModel.refresh() }
                    )
                }

                uiState.isLoading -> {
                    LoadingState()
                }
            }
        }
    }
}
