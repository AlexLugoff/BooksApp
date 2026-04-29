package io.github.alexlugoff.booksapp.core.domain.repository

import io.github.alexlugoff.booksapp.core.model.Book
import io.github.alexlugoff.booksapp.core.utils.Resource

interface BooksRepository {
    suspend fun getBooks(query: String, maxResults: Int): Resource<List<Book>>
    suspend fun getBook(id: String): Resource<Book>
}
