package com.example.booksapp.features.details.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.booksapp.R
import com.example.booksapp.core.model.Book
import com.example.booksapp.core.ui.theme.BooksAppTheme
import com.example.booksapp.core.utils.getAuthorsDisplay
import com.example.booksapp.core.utils.getHttpsImageLink
import com.example.booksapp.core.utils.parseHtml

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
            .height(280.dp),
        contentAlignment = Alignment.Center
    ) {
        // Blur background
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.getHttpsImageLink())
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(20.dp),
            contentScale = ContentScale.Crop,
            alpha = 0.3f
        )

        // Gradient overlay for better contrast
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
        )

        Card(
            modifier = Modifier
                .size(160.dp, 240.dp)
                .padding(vertical = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.getHttpsImageLink())
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
                text = book.getAuthorsDisplay(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            InfoItem(
                icon = Icons.Default.Pages,
                label = book.pageCount?.let { stringResource(R.string.pages_label, it) } ?: "-",
                title = stringResource(R.string.pages_title),
                modifier = Modifier.weight(1f)
            )
            InfoItem(
                icon = Icons.AutoMirrored.Filled.MenuBook,
                label = book.categories.firstOrNull()?.take(12) ?: "-",
                title = stringResource(R.string.category_title),
                modifier = Modifier.weight(1f)
            )
            InfoItem(
                icon = Icons.Default.Public,
                label = book.language?.uppercase() ?: "-",
                title = stringResource(R.string.language_title),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BookDescriptionSection(description: String?) {
    var isExpanded by remember { mutableStateOf(false) }
    var hasOverflow by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .then(
                if (hasOverflow || isExpanded) {
                    Modifier.clickable { isExpanded = !isExpanded }
                } else {
                    Modifier
                }
            )
    ) {
        Text(
            text = stringResource(R.string.about_book),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        val annotatedDescription = description.parseHtml(
            fallback = stringResource(R.string.no_description)
        )

        Text(
            text = annotatedDescription,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Justify,
            maxLines = if (isExpanded) Int.MAX_VALUE else 4,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded) {
                    hasOverflow = textLayoutResult.hasVisualOverflow
                }
            }
        )

        if (hasOverflow || isExpanded) {
            Text(
                text = if (isExpanded) stringResource(R.string.read_less) else stringResource(R.string.read_more),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
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
