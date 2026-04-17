package com.example.booksapp.core.data.repository

import android.content.Context
import com.example.booksapp.R
import com.example.booksapp.core.data.mappers.toBooksList
import com.example.booksapp.core.network.BookApiService
import com.example.booksapp.core.domain.repository.BooksRepository
import com.example.booksapp.core.model.Book
import com.example.booksapp.core.utils.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val bookApiService: BookApiService,
    @param:ApplicationContext private val context: Context
) : BooksRepository {

    override suspend fun getBooks(
        query: String,
        maxResults: Int
    ): Resource<List<Book>> {
        return try {
            val response = bookApiService.bookSearch(query, maxResults)
            Resource.Success(response.toBooksList())
        } catch (e: IOException) {
            Resource.Error(context.getString(R.string.error_network))
        } catch (e: HttpException) {
            if (e.code() == 503) {
                Resource.Error(context.getString(R.string.error_service_unavailable))
            } else {
                Resource.Error(context.getString(R.string.error_server, e.code()))
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: context.getString(R.string.error_unknown))
        }
    }

    override suspend fun getBook(id: String): Resource<Book> {
        return try {
            val itemDto = bookApiService.getBookDetails(id)
            val book = Book(
                id = itemDto.id ?: id,
                title = itemDto.volumeInfo?.title ?: context.getString(R.string.no_title),
                authors = itemDto.volumeInfo?.authors ?: emptyList(),
                description = itemDto.volumeInfo?.description,
                pageCount = itemDto.volumeInfo?.pageCount,
                categories = itemDto.volumeInfo?.categories ?: emptyList(),
                previewLink = itemDto.volumeInfo?.previewLink,
                imageLink = itemDto.volumeInfo?.imageLinks?.thumbnail
            )
            Resource.Success(book)
        } catch (e: IOException) {
            Resource.Error(context.getString(R.string.error_network))
        } catch (e: HttpException) {
            Resource.Error(context.getString(R.string.error_server, e.code()))
        } catch (e: Exception) {
            Resource.Error(e.message ?: context.getString(R.string.error_unknown))
        }
    }
}
