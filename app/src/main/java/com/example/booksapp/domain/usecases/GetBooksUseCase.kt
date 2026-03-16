package com.example.booksapp.domain.usecases

import com.example.booksapp.domain.models.Book
import com.example.booksapp.domain.repository.BooksRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(
        query: String,
        maxResults: Int
    ): List<Book> =
        repository.getBooks(query, maxResults)
}