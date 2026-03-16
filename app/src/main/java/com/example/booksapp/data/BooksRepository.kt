package com.example.booksapp.data

import com.example.booksapp.data.models.Book

interface BooksRepository {
    suspend fun getBooks(query: String, maxResults: Int): List<Book>
}