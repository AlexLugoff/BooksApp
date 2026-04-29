package io.github.alexlugoff.booksapp.core.domain.usecases

import io.github.alexlugoff.booksapp.core.domain.repository.BooksRepository
import io.github.alexlugoff.booksapp.core.model.Book
import io.github.alexlugoff.booksapp.core.utils.Resource
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(id: String): Resource<Book> =
        repository.getBook(id)
}
