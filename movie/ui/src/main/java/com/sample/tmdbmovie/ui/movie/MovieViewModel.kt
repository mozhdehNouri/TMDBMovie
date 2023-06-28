package com.sample.tmdbmovie.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.core.comon.BaseResult
import com.sample.core.comon.launchWithErrorHandler
import com.sample.core.ui.DataResult
import com.sample.core.ui.Status
import com.sample.domain.genre.GenreUseCase
import com.sample.domain.movie.AllMovieUseCase
import com.sample.tmdbmovie.ui.data.MovieUiState
import com.sample.tmdbmovie.ui.data.toGenreUiResponse
import com.sample.tmdbmovie.ui.data.toMovieResponseView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
open class MovieViewModel @Inject constructor(
    private val genre: GenreUseCase,
    private val movie: AllMovieUseCase
) : ViewModel() {

    private val _genreUiResponseView: MutableStateFlow<DataResult<MovieUiState.GenreState?>> =
        MutableStateFlow(
            DataResult(
                responseType = Status.LOADING,
                data = MovieUiState.GenreState(listOf())
            )
        )
    val genreUiResponseView = _genreUiResponseView.asStateFlow()


    private val _movieResponseView: MutableStateFlow<DataResult<MovieUiState.MovieItemState?>> =
        MutableStateFlow(
            DataResult(
                responseType = Status.LOADING,
                data = MovieUiState.MovieItemState(listOf())
            )
        )
    val movieResponseView = _movieResponseView.asStateFlow()

    init {
        getGenre()
        getMovie()
    }

    // TODO: add combine all request
    private fun getGenre() = viewModelScope.launchWithErrorHandler {
        _genreUiResponseView.value =
            DataResult(responseType = Status.LOADING)
        when (val result = genre.invoke()) {
            is BaseResult.Success -> {
                _genreUiResponseView.value = DataResult(
                    responseType = Status.SUCCESSFUL,
                    data = result.data.toGenreUiResponse()
                )
            }

            is BaseResult.Error -> {
                _genreUiResponseView.value = DataResult(
                    responseType = Status.ERROR,
                    error = result.exception
                )
            }
        }
    }


    private fun getMovie() = viewModelScope.launchWithErrorHandler {
        _movieResponseView.value =
            DataResult(responseType = Status.LOADING)
        when (val result = movie.invoke()) {
            is BaseResult.Success -> {
                _movieResponseView.value = DataResult(
                    responseType = Status.SUCCESSFUL,
                    data = result.data.toMovieResponseView()
                )
            }

            is BaseResult.Error -> {
                _movieResponseView.value = DataResult(
                    responseType = Status.ERROR,
                    error = result.exception
                )
            }
        }
    }

}