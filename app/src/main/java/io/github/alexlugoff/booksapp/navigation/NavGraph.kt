package io.github.alexlugoff.booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.alexlugoff.booksapp.features.books.BooksScreen
import io.github.alexlugoff.booksapp.features.details.BookDetailsScreen
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
            BooksScreen(
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
