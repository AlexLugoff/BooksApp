package com.example.booksapp.core.utils

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import com.example.booksapp.core.model.Book

/**
 * Преобразует ссылку на изображение в безопасный протокол HTTPS.
 */
fun Book.getHttpsImageLink(): String? {
    return imageLink?.replace(PROTOCOL_HTTP, PROTOCOL_HTTPS)
}

/**
 * Форматирует список авторов в строку через запятую.
 */
fun Book.getAuthorsDisplay(): String {
    return authors.joinToString(", ")
}

/**
 * Формирует текст для отправки (Share).
 */
fun Book.getShareText(): String {
    return buildString {
        append(title)
        previewLink?.let {
            append("\n")
            append(it)
        }
    }
}

/**
 * Расширение для контекста для реализации функции Share.
 */
fun Context.shareText(text: String) {
    if (text.isBlank()) return
    
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = MIME_TYPE_TEXT
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

/**
 * Парсинг HTML строк в AnnotatedString для Compose.
 */
@Composable
fun String?.parseHtml(fallback: String = ""): AnnotatedString {
    return if (this != null) {
        AnnotatedString.fromHtml(this)
    } else {
        AnnotatedString(fallback)
    }
}
