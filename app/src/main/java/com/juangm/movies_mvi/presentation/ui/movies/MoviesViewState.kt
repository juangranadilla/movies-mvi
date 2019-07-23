package com.juangm.movies_mvi.presentation.ui.movies

import com.juangm.movies_mvi.domain.models.Movie
import com.juangm.movies_mvi.presentation.base.ViewState

data class MoviesViewState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: Throwable? = null
): ViewState