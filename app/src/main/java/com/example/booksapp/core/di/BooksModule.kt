package com.example.booksapp.core.di

import com.example.booksapp.core.data.repository.BooksRepositoryImpl
import com.example.booksapp.core.domain.repository.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BooksModule {

    @Binds
    @Singleton
    abstract fun bindBooksRepository(
        booksRepositoryImpl: BooksRepositoryImpl
    ): BooksRepository
}
