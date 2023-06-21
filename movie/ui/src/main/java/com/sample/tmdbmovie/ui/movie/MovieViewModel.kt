package com.sample.tmdbmovie.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.core.comon.BaseResult
import com.sample.core.ui.DataResult
import com.sample.core.ui.Status
import com.sample.domain.useCase.GenreUseCase
import com.sample.tmdbmovie.ui.data.MovieUiState
import com.sample.tmdbmovie.ui.data.toGenreUiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public open class MovieViewModel @Inject constructor(
    private val genre: GenreUseCase
) : ViewModel() {

    private val _genreUiResponse: MutableStateFlow<DataResult<MovieUiState.GenreUi?>> =
        MutableStateFlow(
            DataResult(
                responseType = Status.LOADING,
                data = MovieUiState.GenreUi(listOf())
            )
        )
    val genreUiResponse = _genreUiResponse.asStateFlow()

    init {
        getGenre()

    }

    // TODO: add combine all request
    private fun getGenre() = viewModelScope.launch {
        _genreUiResponse.value =
            DataResult(responseType = Status.LOADING)
        when (val result = genre.invoke()) {
            is BaseResult.Success -> {
                _genreUiResponse.value = DataResult(
                    responseType = Status.SUCCESSFUL,
                    data = result.data.toGenreUiResponse()
                )
            }

            is BaseResult.Error -> {
                _genreUiResponse.value = DataResult(
                    responseType = Status.ERROR,
                    error = result.exception
                )
            }
        }
    }


}