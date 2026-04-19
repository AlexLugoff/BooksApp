package com.example.booksapp.core.data.repository

import android.content.Context
import com.example.booksapp.R
import com.example.booksapp.core.network.BookApiService
import com.example.booksapp.core.network.model.BookShelfDto
import com.example.booksapp.core.utils.Resource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class BooksRepositoryTest {

    private val apiService = mockk<BookApiService>()
    private val context = mockk<Context>()
    private lateinit var repository: BooksRepositoryImpl

    private val testErrorNetwork = "Network Error"

    @Before
    fun setup() {
        repository = BooksRepositoryImpl(apiService, context)
        // Mock context strings
        every { context.getString(R.string.error_network) } returns testErrorNetwork
    }

    @Test
    fun `getBooks returns success resource when api call is successful`() = runTest {
        // Arrange
        val mockResponse = BookShelfDto(items = arrayListOf())
        coEvery { apiService.bookSearch(any(), any()) } returns mockResponse

        // Act
        val result = repository.getBooks("query", 10)

        // Assert
        assertTrue(result is Resource.Success)
        assertEquals(0, (result as Resource.Success).data?.size)
    }

    @Test
    fun `getBooks returns error resource when io exception occurs`() = runTest {
        // Arrange
        coEvery { apiService.bookSearch(any(), any()) } throws IOException()

        // Act
        val result = repository.getBooks("query", 10)

        // Assert
        assertTrue(result is Resource.Error)
        assertEquals(testErrorNetwork, (result as Resource.Error).message)
    }
}
