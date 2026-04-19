package com.example.booksapp.core.domain.repository

import com.example.booksapp.core.model.Book
import com.example.booksapp.core.utils.Resource

interface BooksRepository {
    suspend fun getBooks(query: String, maxResults: Int): Resource<List<Book>>
    suspend fun getBook(id: String): Resource<Book>
}
