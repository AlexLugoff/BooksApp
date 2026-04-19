package com.example.booksapp.features.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.core.domain.usecases.GetBooksUseCase
import com.example.booksapp.core.utils.DEFAULT_SEARCH_BOOK_PAGE_SIZE
import com.example.booksapp.core.utils.DEFAULT_SEARCH_BOOK_QUERY
import com.example.booksapp.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class BooksUiEffect {
    data class ShowSnackbar(val message: String) : BooksUiEffect()
}

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BooksState())
    val state: StateFlow<BooksState> = _state.asStateFlow()

    private val _effect = Channel<BooksUiEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        handleIntent(BooksIntent.Refresh)
    }

    fun handleIntent(intent: BooksIntent) {
        when (intent) {
            is BooksIntent.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = intent.query) }
            }

            is BooksIntent.SearchClicked -> {
                _state.update { it.copy(searchWidgetState = SearchWidgetState.CLOSED) }
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
                    result.message?.let {
                        _effect.send(BooksUiEffect.ShowSnackbar(it))
                    }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
