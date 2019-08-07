package com.juangm.domain.action

sealed class MoviesAction: Action {
    object GetTopRatedMoviesAction: MoviesAction()
    object LoadMoreTopRatedMoviesAction: MoviesAction()
}