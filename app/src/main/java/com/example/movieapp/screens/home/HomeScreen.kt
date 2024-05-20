package com.example.movieapp.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary,
      ), title = {
        Text("Movies")
      })
    },
  ) { innerPadding ->
    MainContent(
      innerPadding = innerPadding,
      navController = navController
    )
  }
}

@Composable
fun MainContent(
  innerPadding: PaddingValues,
  navController: NavController,
  movieList: List<Movie> = getMovies()
) {
  Column(
    modifier = Modifier.padding(innerPadding),
  ) {
    LazyColumn(modifier = Modifier.padding(12.dp)) {
      items(
        items = movieList
      ) {
        MovieRow(it) { movie ->
          navController.navigate(route = MovieScreens.DetailScreen.name + "/$movie")
          Log.d("movie", "MainContent: $movie")
        }
      }
    }
  }
}
