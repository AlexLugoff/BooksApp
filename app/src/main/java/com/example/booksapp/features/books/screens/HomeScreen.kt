package com.example.booksapp.features.books.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.booksapp.core.model.Book
import com.example.booksapp.core.ui.components.LoadingState
import com.example.booksapp.features.books.models.BooksState

@Composable
fun HomeScreen(
    state: BooksState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    when {
        state.isLoading -> LoadingState(modifier)
        state.isError -> ErrorScreen(
            onRetry,
            modifier
        ) // Keeping ErrorScreen because it has Retry button
        else -> BooksGridScreen(
            books = state.books,
            modifier = modifier,
            onBookClicked = onBookClicked
        )
    }
}
