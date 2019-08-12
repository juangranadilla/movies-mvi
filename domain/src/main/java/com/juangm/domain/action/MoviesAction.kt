package com.juangm.domain.action

sealed class MoviesAction: Action {
    object GetPopularMoviesAction: MoviesAction()
    object LoadMorePopularMoviesAction: MoviesAction()
    object GetTopRatedMoviesAction: MoviesAction()
    object LoadMoreTopRatedMoviesAction: MoviesAction()
    object GetUpcomingMoviesAction: MoviesAction()
    object LoadMoreUpcomingMoviesAction: MoviesAction()
}