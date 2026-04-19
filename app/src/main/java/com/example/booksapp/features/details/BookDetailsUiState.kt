package com.example.booksapp.features.details

import com.example.booksapp.core.model.Book

data class BookDetailsUiState(
    val book: Book? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)