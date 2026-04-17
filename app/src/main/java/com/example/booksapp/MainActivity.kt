package com.example.booksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.booksapp.core.ui.theme.BooksAppTheme
import com.example.booksapp.navigation.BooksNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BooksAppTheme {
                val navController = rememberNavController()
                BooksNavGraph(
                    navController = navController
                )
            }
        }
    }
}
