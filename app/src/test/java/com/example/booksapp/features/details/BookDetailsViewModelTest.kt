package com.example.booksapp.features.details

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.booksapp.core.domain.usecases.GetBookDetailsUseCase
import com.example.booksapp.core.model.Book
import com.example.booksapp.core.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookDetailsViewModelTest {

    private val useCase = mockk<GetBookDetailsUseCase>()
    private lateinit var viewModel: BookDetailsViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val bookId = "test_id"

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel() {
        // Create SavedStateHandle with the required "id" argument
        val savedStateHandle = SavedStateHandle(mapOf("id" to bookId))
        viewModel = BookDetailsViewModel(useCase, savedStateHandle)
    }

    @Test
    fun `init loads book details successfully`() = runTest {
        val book = Book(id = bookId, title = "Test Book")
        coEvery { useCase(bookId) } returns Resource.Success(book)

        createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(book, state.book)
            assertEquals(false, state.isLoading)
        }
    }

    @Test
    fun `init updates state with error when use case fails`() = runTest {
        val errorMessage = "Error loading book"
        coEvery { useCase(bookId) } returns Resource.Error(errorMessage)

        createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(errorMessage, state.error)
            assertEquals(false, state.isLoading)
        }
    }

    @Test
    fun `error effect is sent when loading fails`() = runTest {
        val errorMessage = "Network error"
        coEvery { useCase(bookId) } returns Resource.Error(errorMessage)

        createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.effect.test {
            val effect = awaitItem()
            assert(effect is BookDetailsUiEffect.ShowSnackbar)
            assertEquals(errorMessage, (effect as BookDetailsUiEffect.ShowSnackbar).message)
        }
    }
}
