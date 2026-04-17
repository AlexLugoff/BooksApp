package com.example.booksapp.features.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.booksapp.core.domain.usecases.GetBookDetailsUseCase
import com.example.booksapp.core.utils.Resource
import com.example.booksapp.features.details.models.BookDetailsUiState
import com.example.booksapp.navigation.BookDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBookDetailsUseCase: GetBookDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val bookDetailsRoute: BookDetailsRoute = savedStateHandle.toRoute()
    private val bookId = bookDetailsRoute.id

    private val _uiState = MutableStateFlow(BookDetailsUiState())
    val uiState: StateFlow<BookDetailsUiState> = _uiState.asStateFlow()

    init {
        getBookDetails()
    }

    private fun getBookDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = getBookDetailsUseCase(bookId)) {
                is Resource.Success -> {
                    _uiState.update { it.copy(book = result.data, isLoading = false) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message, isLoading = false) }
                }
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
