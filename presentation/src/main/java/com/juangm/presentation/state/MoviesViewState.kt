package com.juangm.presentation.state

import com.juangm.domain.models.Movie

data class MoviesViewState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: Throwable? = null
): ViewState