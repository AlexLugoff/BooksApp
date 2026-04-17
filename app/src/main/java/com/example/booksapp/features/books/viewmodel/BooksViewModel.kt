package com.example.booksapp.features.books.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.DEFAULT_SEARCH_BOOK_PAGE_SIZE
import com.example.booksapp.DEFAULT_SEARCH_BOOK_QUERY
import com.example.booksapp.core.utils.Resource
import com.example.booksapp.core.domain.usecases.GetBooksUseCase
import com.example.booksapp.features.books.models.BooksIntent
import com.example.booksapp.features.books.models.BooksState
import com.example.booksapp.features.books.models.SearchWidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BooksState())
    val state: StateFlow<BooksState> = _state.asStateFlow()

    init {
        handleIntent(BooksIntent.Refresh)
    }

    fun handleIntent(intent: BooksIntent) {
        when (intent) {
            is BooksIntent.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = intent.query) }
            }

            is BooksIntent.SearchClicked -> {
                getBooks(intent.query)
            }

            BooksIntent.ToggleSearchWidget -> {
                _state.update { it.copy(searchWidgetState = SearchWidgetState.OPENED) }
            }

            BooksIntent.CloseSearchWidget -> {
                _state.update {
                    it.copy(
                        searchWidgetState = SearchWidgetState.CLOSED,
                        searchQuery = ""
                    )
                }
            }

            BooksIntent.Refresh -> {
                _state.update { it.copy(searchWidgetState = SearchWidgetState.CLOSED) }
                getBooks(_state.value.searchQuery.ifEmpty { DEFAULT_SEARCH_BOOK_QUERY })
            }
        }
    }

    private fun getBooks(
        query: String,
        maxResults: Int = DEFAULT_SEARCH_BOOK_PAGE_SIZE
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }
            when (val result = getBooksUseCase(query, maxResults)) {
                is Resource.Success -> {
                    _state.update { 
                        it.copy(
                            books = result.data ?: emptyList(), 
                            isLoading = false 
                        ) 
                    }
                }
                is Resource.Error -> {
                    _state.update { 
                        it.copy(
                            isLoading = false, 
                            isError = true,
                            errorMessage = result.message
                        ) 
                    }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
