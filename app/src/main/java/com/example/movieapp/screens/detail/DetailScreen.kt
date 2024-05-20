package com.example.movieapp.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
  navController: NavController, movieId: String?
) {
  val newMovieList = getMovies().filter { movie: Movie -> movie.id == movieId }

  Scaffold(topBar = {
    CenterAlignedTopAppBar(
      modifier = Modifier.shadow(
        elevation = 4.dp
      ),
      colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Transparent,
        titleContentColor = MaterialTheme.colorScheme.primary,
      ),
      title = {
        Text(text = "Detail View")
      },
      navigationIcon = {
        IconButton(onClick = {
          navController.popBackStack()
        }) {
          Icon(
            imageVector = Icons.Filled.ArrowBack, contentDescription = "Localized description"
          )
        }
      },
    )
  }) { innerPadding ->
    Surface(
      modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
      ) {
        MovieRow(movie = newMovieList.first())

        Spacer(
          modifier = Modifier
            .height(8.dp)
        )

        Divider()

        Text(text = "Movie Images")

        HorizontalScrollableImageView(newMovieList)
      }
    }
  }
}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
  LazyRow {
    items(newMovieList.first().images) { image ->
      Card(
        modifier = Modifier
          .padding(12.dp),
        elevation = CardDefaults.cardElevation(5.dp),
      ) {
        AsyncImage(
          model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
          contentDescription = "Movie Poster",
        )
      }
    }
  }
}