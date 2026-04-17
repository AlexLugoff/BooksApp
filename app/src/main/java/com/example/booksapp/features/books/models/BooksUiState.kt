package com.example.booksapp.features.books.models

import com.example.booksapp.core.model.Book

/**
 * State representing the Books Screen.
 */
data class BooksState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val searchWidgetState: SearchWidgetState = SearchWidgetState.CLOSED
)

enum class SearchWidgetState {
    OPENED, CLOSED
}

/**
 * UI Intents representing user actions on the Books Screen.
 */
sealed interface BooksIntent {
    data class SearchQueryChanged(val query: String) : BooksIntent
    data class SearchClicked(val query: String) : BooksIntent
    object ToggleSearchWidget : BooksIntent
    object CloseSearchWidget : BooksIntent
    object Refresh : BooksIntent
}
