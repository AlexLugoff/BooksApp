package com.example.booksapp.features.books.screens

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
import com.example.booksapp.core.ui.theme.BooksAppTheme
import com.example.booksapp.features.books.components.BookCard

@Composable
fun BooksGridScreen(
    books: List<Book>,
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
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
