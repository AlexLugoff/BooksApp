package com.example.booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booksapp.features.books.BooksApp
import com.example.booksapp.features.details.screens.BookDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
object BooksRoute

@Serializable
data class BookDetailsRoute(val id: String)

@Composable
fun BooksNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BooksRoute,
        modifier = modifier
    ) {
        composable<BooksRoute> {
            BooksApp(
                onBookClicked = { book ->
                    navController.navigate(BookDetailsRoute(id = book.id))
                }
            )
        }
        composable<BookDetailsRoute> {
            BookDetailsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
