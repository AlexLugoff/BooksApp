package com.example.booksapp.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.core.domain.usecases.GetBookDetailsUseCase
import com.example.booksapp.core.utils.Resource
import com.example.booksapp.navigation.BookDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.navigation.toRoute

sealed class BookDetailsUiEffect {
    data class ShowSnackbar(val message: String) : BookDetailsUiEffect()
}

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBookDetailsUseCase: GetBookDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val bookDetailsRoute: BookDetailsRoute = savedStateHandle.toRoute()
    private val bookId = bookDetailsRoute.id

    private val _uiState = MutableStateFlow(BookDetailsUiState())
    val uiState: StateFlow<BookDetailsUiState> = _uiState.asStateFlow()

    private val _effect = Channel<BookDetailsUiEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        getBookDetails()
    }

    fun refresh() {
        getBookDetails()
    }

    private fun getBookDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = getBookDetailsUseCase(bookId)) {
                is Resource.Success -> {
                    _uiState.update { it.copy(book = result.data, isLoading = false, error = null) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message, isLoading = false) }
                    result.message?.let {
                        _effect.send(BookDetailsUiEffect.ShowSnackbar(it))
                    }
                }
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
