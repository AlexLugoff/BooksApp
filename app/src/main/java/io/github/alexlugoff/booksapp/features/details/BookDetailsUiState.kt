package io.github.alexlugoff.booksapp.features.details

import io.github.alexlugoff.booksapp.core.model.Book

data class BookDetailsUiState(
    val book: Book? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
