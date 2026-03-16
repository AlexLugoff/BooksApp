package com.example.booksapp.domain.repository

import com.example.booksapp.domain.models.Book

interface BooksRepository {
    suspend fun getBooks(query: String, maxResults: Int): List<Book>
}