package com.example.booksapp.data.remote

import com.example.booksapp.BookShelfDto
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface BookApiService {

    companion object {
        const val CONNECT_TIMEOUT = 30L
        const val READ_TIMEOUT = 30L
        const val WRITE_TIMEOUT = 30L

        val timeUnit = TimeUnit.SECONDS
    }

    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") searchQuery: String,
        @Query("maxResults") maxResults: Int
    ): BookShelfDto
}