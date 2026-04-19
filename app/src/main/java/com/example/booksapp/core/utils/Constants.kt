package com.example.booksapp.core.utils

/**
 * Глобальные константы приложения.
 * Вынесены в отдельный файл для удобства поддержки и соблюдения принципа DRY.
 */

// Network
const val BASE_URL = "https://www.googleapis.com/books/v1/"
const val PROTOCOL_HTTP = "http://"
const val PROTOCOL_HTTPS = "https://"

// MIME Types
const val MIME_TYPE_TEXT = "text/plain"

// Search Defaults
const val DEFAULT_SEARCH_BOOK_QUERY = "Android development"
const val DEFAULT_SEARCH_BOOK_PAGE_SIZE = 20

// UI Constants
const val BOOK_CARD_ASPECT_RATIO = 0.7f
const val BOOK_DETAILS_HEADER_HEIGHT_DP = 280
const val BOOK_DETAILS_COVER_WIDTH_DP = 160
const val BOOK_DETAILS_COVER_HEIGHT_DP = 240
const val BLUR_RADIUS_DP = 20
const val GRID_ADAPTIVE_CELL_MIN_SIZE_DP = 160

// Animation
const val CROSSFADE_DURATION_MS = 500
const val ANIMATION_LABEL_APP_BAR = "AppBarAnimation"
