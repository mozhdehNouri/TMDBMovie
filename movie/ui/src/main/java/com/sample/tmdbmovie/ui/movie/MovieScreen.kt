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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.core.ui.Status
import com.sample.tmdbmovie.ui.data.GenreListUi
import com.sample.tmdbmovie.ui.data.MovieUiState
import com.example.tmdbmovie.movie.ui.R as MovieResource
import com.sample.core.R as CoreResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val state by viewModel.genreUiResponse.collectAsStateWithLifecycle()

    var selected by remember { mutableStateOf("") }

    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        Column(modifier = modifier.padding(padding)) {
            when (state.responseType) {
                Status.SUCCESSFUL -> {
                    MovieListScreenContent(
                        selected = selected,
                        onSelected = {
                            selected = it
                        },
                        genreUiState = state.data
                    )
                }

                Status.LOADING -> {
                    CircularProgressIndicator(0.5f)
                }

                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,
                        "${state.error}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }

    }
}

@Composable
private fun MovieListScreenContent(
    selected: String,
    onSelected: (String) -> Unit,
    genreUiState: MovieUiState.GenreUi?
) {
    Column() {
        ChipGroup(
            selected = selected, onSelected = {
                onSelected(it)
            },
            genreList = genreUiState?.genres
        )

    }

}


@Composable
private fun MovieListItem(list: GenreListUi) {
    Column(verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = MovieResource.drawable.ic_star_rate),
            contentDescription = ""
        )
        Text(text = list.name)
        Row() {
            Image(
                painter = painterResource(id = MovieResource.drawable.ic_star_rate),
                contentDescription = ""
            )
            Text(text = "6.7")
        }
    }
}

@Composable
private fun ChipGroup(
    genreList: List<GenreListUi>?,
    selected: String,
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit
) {
    val state = rememberScrollState()
    Row(
        modifier = modifier
            .padding(20.dp)
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
    val background = if (isSelected) Color.LightGray else Color.Black
    val contentColor = if (isSelected) Color.Green else Color.Red

    Box(
        modifier = modifier
            .padding(10.dp)
            .clip(CircleShape)
            .background(background)
            .clickable {
                onSelected(title)
            }
    ) {
        Row(modifier.padding(15.dp)) {
            AnimatedVisibility(visible = isSelected) {
                Icon(
                    painter = painterResource(id = MovieResource.drawable.ic_check),
                    contentDescription = ""
                )
            }
            Text(
                text = title, modifier = modifier.padding(
                    dimensionResource(id = CoreResource.dimen.spacing_2x)
                ), color = contentColor
            )
        }
    }
}

