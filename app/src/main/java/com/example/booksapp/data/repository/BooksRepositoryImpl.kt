package com.example.booksapp.data.repository

import com.example.booksapp.data.mappers.toBooksList
import com.example.booksapp.domain.models.Book
import com.example.booksapp.domain.repository.BooksRepository
import com.example.booksapp.data.remote.BookApiService
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val bookApiService: BookApiService
) : BooksRepository {

    override suspend fun getBooks(
        query: String,
        maxResults: Int
    ): List<Book> = bookApiService.bookSearch(query, maxResults).toBooksList()

}