package com.example.booksapp.features.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Pages
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.booksapp.R
import com.example.booksapp.core.model.Book
import com.example.booksapp.core.ui.theme.BooksAppTheme

@Composable
fun BookDetailsContent(
    book: Book,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BookHeader(book)

        Column(modifier = Modifier.padding(24.dp)) {
            BookInfoSection(book)
            
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(24.dp))

            BookDescriptionSection(book.description)
        }
    }
}

@Composable
private fun BookHeader(book: Book) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .size(160.dp, 240.dp)
                .padding(vertical = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.imageLink?.replace("http", "https"))
                    .crossfade(true)
                    .build(),
                contentDescription = book.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_broken_image)
            )
        }
    }
}

@Composable
private fun BookInfoSection(book: Book) {
    Column {
        Text(
            text = book.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (book.authors.isNotEmpty()) {
            Text(
                text = book.authors.joinToString(", "),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoItem(
                icon = Icons.Default.Pages,
                label = book.pageCount?.let { stringResource(R.string.pages_label, it) } ?: "-",
                modifier = Modifier.weight(1f)
            )
            InfoItem(
                icon = Icons.AutoMirrored.Filled.MenuBook,
                label = book.categories.firstOrNull() ?: "-",
                modifier = Modifier.weight(1f)
            )
            InfoItem(
                icon = Icons.Default.Public,
                label = stringResource(R.string.language_en),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BookDescriptionSection(description: String?) {
    Column {
        Text(
            text = stringResource(R.string.about_book),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description ?: stringResource(R.string.no_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BookDetailsContentPreview() {
    BooksAppTheme {
        BookDetailsContent(
            book = Book(
                id = "1",
                title = "Effective Kotlin",
                authors = listOf("Marcin Moskala"),
                description = "Best practices for Kotlin development. This book will help you write better Kotlin code.",
                pageCount = 450,
                categories = listOf("Programming", "Kotlin"),
                imageLink = "https://example.com/image.jpg"
            )
        )
    }
}
