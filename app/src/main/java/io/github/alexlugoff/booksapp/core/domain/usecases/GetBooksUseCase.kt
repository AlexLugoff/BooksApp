package io.github.alexlugoff.booksapp.core.domain.usecases

import io.github.alexlugoff.booksapp.core.domain.repository.BooksRepository
import io.github.alexlugoff.booksapp.core.model.Book
import io.github.alexlugoff.booksapp.core.utils.Resource
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
