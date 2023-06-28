package com.sample.tmdbmovie.ui.movie

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.core.BuildConfig
import com.sample.core.ui.DynamicAsyncImage
import com.sample.core.ui.Status
import com.sample.tmdbmovie.ui.data.GenreItemResponseView
import com.sample.tmdbmovie.ui.data.MovieItemResponseView
import com.sample.tmdbmovie.ui.data.MovieUiState
import com.example.tmdbmovie.movie.ui.R as MovieResource
import com.sample.core.R as CoreResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val genreState by viewModel.genreUiResponseView.collectAsStateWithLifecycle()
    val movieState by viewModel.movieResponseView.collectAsStateWithLifecycle()

    var selected by remember { mutableStateOf("") }

    Scaffold(modifier = modifier.fillMaxSize()) { padding ->

        val context = LocalContext.current

        when {
            genreState.responseType == Status.LOADING && movieState.responseType == Status.LOADING -> {
                CircularProgressIndicator(
                    color = Color.Red,
                    progress = 0.5f
                )
            }

            genreState.responseType == Status.SUCCESSFUL
                    && movieState.responseType == Status.SUCCESSFUL -> {

                MovieListScreenContent(
                    selected = selected,
                    onSelected = {
                        selected = it
                    },
                    genreUiState = genreState.data,
                    movieList = movieState.data!!
                )
            }

            genreState.responseType == Status.ERROR || movieState.responseType == Status.ERROR -> {
                Toast.makeText(
                    context,
                    genreState.error?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        }
}

@Composable
private fun MovieListScreenContent(
    selected: String,
    onSelected: (String) -> Unit,
    genreUiState: MovieUiState.GenreState?,
    movieList: MovieUiState.MovieItemState
) {
    Column(Modifier.padding(dimensionResource(id = CoreResource.dimen.spacing_2x))) {
        ChipGroup(
            selected = selected, onSelected = {
                onSelected(it)
            },
            genreList = genreUiState?.genres
        )
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(items = movieList.movie) { movie ->
                MovieListItem(movie)
            }
        }

    }

}


@Composable
private fun MovieListItem(
    movieItem: MovieItemResponseView,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Card() {
            DynamicAsyncImage(
                imageUrl = BuildConfig.IMAGE_BASE_URL + movieItem.backdropPath,
                contentDescription = null,
                placeholder = painterResource(id = MovieResource.drawable.ic_check)
            )
        }
        Text(
            text = movieItem.title,
            Modifier.padding(dimensionResource(id = CoreResource.dimen.spacing_base))
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.padding(
                dimensionResource(id = CoreResource.dimen.spacing_base)
            )
        ) {
            Image(
                painter = painterResource(id = MovieResource.drawable.ic_star_rate),
                contentDescription = ""
            )
            Text(text = movieItem.voteAverage.toString())
        }
    }
}

@Composable
private fun ChipGroup(
    genreList: List<GenreItemResponseView>?,
    selected: String,
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit
) {
    val state = rememberScrollState()
    Row(
        modifier = modifier
            .horizontalScroll(state)
    )
    {
        genreList?.forEach { gener ->
            Chip(
                title = gener.name,
                selected = selected,
                onSelected = { onSelected.invoke(it) })
        }
    }
}

@Composable
private fun Chip(
    title: String,
    selected: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelected = selected == title
    val background =
        if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.inverseOnSurface
    val contentColor =
        if (isSelected) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = modifier
            .padding(dimensionResource(id = CoreResource.dimen.spacing_2x))
            .clip(CircleShape)
            .background(background)
            .clickable {
                onSelected(title)
            }
    ) {
        Row(
            modifier = modifier.padding(dimensionResource(id = CoreResource.dimen.spacing_3x))
        ) {
            AnimatedVisibility(visible = isSelected) {
                Icon(
                    painter = painterResource(id = MovieResource.drawable.ic_check),
                    contentDescription = ""
                )
            }
            Text(
                text = title, color = contentColor
            )
        }
    }
}
