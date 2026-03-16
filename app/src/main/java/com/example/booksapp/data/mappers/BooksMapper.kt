package com.example.booksapp.data.mappers

import com.example.booksapp.BookShelf
import com.example.booksapp.data.models.Book

fun BookShelf.toBooksList(): List<Book> {
    return this.items.map {
        Book(
            title = it.volumeInfo?.title,
            previewLink = it.volumeInfo?.previewLink,
            imageLink = it.volumeInfo?.imageLinks?.thumbnail
        )
    } ?: emptyList()
}