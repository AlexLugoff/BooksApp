package com.example.booksapp.core.utils

import com.example.booksapp.core.model.Book
import org.junit.Assert.assertEquals
import org.junit.Test

class BookExtensionsTest {

    @Test
    fun `getAuthorsDisplay returns formatted authors string`() {
        val book = Book(id = "1", title = "Title", authors = listOf("Author 1", "Author 2"))
        assertEquals("Author 1, Author 2", book.getAuthorsDisplay())
    }

    @Test
    fun `getAuthorsDisplay returns empty string when authors list is empty`() {
        val book = Book(id = "1", title = "Title", authors = emptyList())
        assertEquals("", book.getAuthorsDisplay())
    }

    @Test
    fun `getHttpsImageLink converts http to https`() {
        val book = Book(id = "1", title = "Title", imageLink = "http://example.com/image.jpg")
        assertEquals("https://example.com/image.jpg", book.getHttpsImageLink())
    }

    @Test
    fun `getHttpsImageLink returns same link if already https`() {
        val book = Book(id = "1", title = "Title", imageLink = "https://example.com/image.jpg")
        assertEquals("https://example.com/image.jpg", book.getHttpsImageLink())
    }

    @Test
    fun `getHttpsImageLink returns null if imageLink is null`() {
        val book = Book(id = "1", title = "Title", imageLink = null)
        assertEquals(null, book.getHttpsImageLink())
    }
}
