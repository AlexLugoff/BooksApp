package com.example.booksapp.core.network

import com.example.booksapp.core.network.model.BookShelfDto
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

    @GET("volumes/{volumeId}")
    suspend fun getBookDetails(
        @retrofit2.http.Path("volumeId") volumeId: String
    ): com.example.booksapp.core.network.model.ItemsDto
}
