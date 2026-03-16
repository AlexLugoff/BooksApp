package com.example.booksapp.data.mappers

import com.example.booksapp.BookShelfDto
import com.example.booksapp.domain.models.Book

fun BookShelfDto.toBooksList(): List<Book> {
    return this.items.map {
        Book(
            title = it.volumeInfo?.title,
            previewLink = it.volumeInfo?.previewLink,
            imageLink = it.volumeInfo?.imageLinks?.thumbnail
        )
    } ?: emptyList()
}