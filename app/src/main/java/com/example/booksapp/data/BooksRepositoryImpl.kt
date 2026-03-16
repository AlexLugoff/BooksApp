package com.example.booksapp.data

import com.example.booksapp.data.mappers.toBooksList
import com.example.booksapp.data.models.Book
import com.example.booksapp.network.BookApiService
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val bookApiService: BookApiService
) : BooksRepository {

    override suspend fun getBooks(
        query: String,
        maxResults: Int
    ): List<Book> = bookApiService.bookSearch(query, maxResults).toBooksList()

}