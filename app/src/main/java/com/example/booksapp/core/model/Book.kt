package com.example.booksapp.core.model

data class Book(
    val id: String,
    val title: String,
    val authors: List<String> = emptyList(),
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String> = emptyList(),
    val language: String? = null,
    val previewLink: String? = null,
    val imageLink: String? = null
)
