package com.example.booksapp.core.data.mappers

import com.example.booksapp.core.network.model.BookShelfDto
import com.example.booksapp.core.model.Book

fun BookShelfDto.toBooksList(): List<Book> {
    return this.items.map {
        Book(
            id = it.id ?: it.hashCode().toString(),
            title = it.volumeInfo?.title ?: "",
            authors = it.volumeInfo?.authors ?: emptyList(),
            description = it.volumeInfo?.description,
            pageCount = it.volumeInfo?.pageCount,
            categories = it.volumeInfo?.categories ?: emptyList(),
            language = it.volumeInfo?.language,
            previewLink = it.volumeInfo?.previewLink,
            imageLink = it.volumeInfo?.imageLinks?.thumbnail
        )
    }
}
