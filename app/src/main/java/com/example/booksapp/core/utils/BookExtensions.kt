package com.example.booksapp.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import com.example.booksapp.core.model.Book

fun Book.getHttpsImageLink(): String? {
    return imageLink?.replace("http://", "https://")
}

fun Book.getAuthorsDisplay(): String {
    return authors.joinToString(", ")
}

@Composable
fun String?.parseHtml(fallback: String = ""): AnnotatedString {
    return if (this != null) {
        AnnotatedString.fromHtml(this)
    } else {
        AnnotatedString(fallback)
    }
}
