package com.example.booksapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.booksapp.ui.BooksApp
import com.example.booksapp.ui.theme.BooksAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BooksAppTheme {
                BooksApp(
                    onBookClicked = {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.previewLink)))
                    }
                )
            }
        }
    }
}