package com.example.booksapp.features.books

import app.cash.turbine.test
import com.example.booksapp.core.domain.usecases.GetBooksUseCase
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
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BooksViewModelTest {

    private val useCase = mockk<GetBooksUseCase>()
    private lateinit var viewModel: BooksViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init loads books successfully`() = runTest {
        val books = listOf(Book(id = "1", title = "Test Book"))
        coEvery { useCase(any(), any()) } returns Resource.Success(books)

        viewModel = BooksViewModel(useCase)
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.state.test {
            val state = awaitItem()
            assertEquals(books, state.books)
            assertEquals(false, state.isLoading)
        }
    }

    @Test
    fun `init updates state with error when use case fails`() = runTest {
        val errorMessage = "Error loading books"
        coEvery { useCase(any(), any()) } returns Resource.Error(errorMessage)

        viewModel = BooksViewModel(useCase)
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.state.test {
            val state = awaitItem()
            assertTrue(state.isError)
            assertEquals(errorMessage, state.errorMessage)
        }
    }
}
