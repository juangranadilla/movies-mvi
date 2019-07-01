package com.juangm.movies_mvi.domain.action

sealed class MoviesAction: Action {
    object GetTopRatedMoviesAction: MoviesAction()
}