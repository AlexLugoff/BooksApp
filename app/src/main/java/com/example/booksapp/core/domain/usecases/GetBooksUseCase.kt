package com.example.booksapp.core.domain.usecases

import com.example.booksapp.core.domain.repository.BooksRepository
import com.example.booksapp.core.model.Book
import com.example.booksapp.core.utils.Resource
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(
        query: String,
        maxResults: Int
    ): Resource<List<Book>> =
        repository.getBooks(query, maxResults)
}
