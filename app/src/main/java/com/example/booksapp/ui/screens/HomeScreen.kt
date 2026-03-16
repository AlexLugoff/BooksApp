package com.example.booksapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.booksapp.data.models.Book
import com.example.booksapp.ui.BooksUiState

@Composable
fun HomeScreen(
    bookUiState: BooksUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    when (bookUiState) {
        is BooksUiState.Loading -> LoadingScreen(modifier)
        is BooksUiState.Success -> BooksGridScreen(
            books = bookUiState.booksSearch,
            modifier = modifier,
            onBookClicked
        )

        is BooksUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}