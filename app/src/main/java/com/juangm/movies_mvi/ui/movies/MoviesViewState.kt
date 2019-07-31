package com.juangm.movies_mvi.ui.movies

import com.juangm.domain.models.Movie
import com.juangm.movies_mvi.base.ViewState

data class MoviesViewState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: Throwable? = null
): ViewState