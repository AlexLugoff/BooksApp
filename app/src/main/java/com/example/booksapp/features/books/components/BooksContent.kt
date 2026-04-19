package com.example.booksapp.features.books.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksapp.core.model.Book
import com.example.booksapp.core.ui.components.ErrorScreen
import com.example.booksapp.core.ui.components.LoadingState
import com.example.booksapp.core.ui.theme.BooksAppTheme
import com.example.booksapp.core.utils.GRID_ADAPTIVE_CELL_MIN_SIZE_DP
import com.example.booksapp.features.books.BooksState

@Composable
fun BooksContent(
    state: BooksState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    when {
        state.isLoading -> LoadingState(modifier)
        state.isError -> ErrorScreen(
            message = state.errorMessage,
            onRetry = onRetry,
            modifier = modifier
        )
        else -> BooksGridScreen(
            books = state.books,
            modifier = modifier,
            onBookClicked = onBookClicked
        )
    }
}

@Composable
fun BooksGridScreen(
    books: List<Book>,
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(GRID_ADAPTIVE_CELL_MIN_SIZE_DP.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(books, key = { it.id }) { book ->
            BookCard(
                book = book,
                onBookClicked = onBookClicked,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BooksGridScreenPreview() {
    BooksAppTheme {
        BooksGridScreen(
            books = listOf(
                Book(id = "1", title = "Kotlin in Action", authors = listOf("Dmitry Jemerov")),
                Book(id = "2", title = "Clean Code", authors = listOf("Robert C. Martin")),
                Book(id = "3", title = "Effective Java", authors = listOf("Joshua Bloch"))
            ),
            onBookClicked = {}
        )
    }
}
