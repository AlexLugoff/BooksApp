package com.example.booksapp.ui

import com.example.booksapp.data.models.Book

sealed interface BooksUiState {
    data class Success(val booksSearch: List<Book>) : BooksUiState
    object Loading : BooksUiState
    object Error : BooksUiState
}