package com.juangm.movies_mvi.presentation.ui.movies

import androidx.lifecycle.liveData
import com.juangm.movies_mvi.domain.action.MoviesAction
import com.juangm.movies_mvi.domain.result.MoviesResult
import com.juangm.movies_mvi.domain.usecase.TopRatedMoviesUseCase
import com.juangm.movies_mvi.presentation.base.BaseViewModel

class MoviesViewModel(
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase
): BaseViewModel<MoviesViewState, MoviesAction, MoviesResult>() {

    override val internalViewState = MoviesViewState()

    override fun handle(action: MoviesAction) = liveData<MoviesResult> {
        when(action) {
            is MoviesAction.GetTopRatedMoviesAction -> topRatedMoviesUseCase.getTopRatedMovies(this)
        }
    }

    override fun reduce(result: MoviesResult): MoviesViewState {
        return when(result) {
            is MoviesResult.Loading -> internalViewState.copy(isLoading = true)
            is MoviesResult.Success -> internalViewState.copy(isLoading = false, movies = result.movies, error = null)
            is MoviesResult.Failure -> internalViewState.copy(isLoading = false, error = Throwable(result.errorMessage))
        }
    }
}