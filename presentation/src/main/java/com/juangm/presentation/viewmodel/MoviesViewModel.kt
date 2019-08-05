package com.juangm.presentation.viewmodel

import androidx.lifecycle.liveData
import com.juangm.domain.action.MoviesAction
import com.juangm.domain.result.MoviesResult
import com.juangm.domain.usecase.TopRatedMoviesUseCase
import com.juangm.presentation.state.MoviesViewState

class MoviesViewModel(
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase
): BaseViewModel<MoviesViewState, MoviesAction, MoviesResult>() {

    /**
     * This is the last view state. We initialize it here, and we will use it in the reduce method,
     * with the data class copy function, to create the new state, depending on the received result
     */
    override val internalViewState = MoviesViewState()

    /**
     * This is a [BaseViewModel] method we have to implement in every ViewModel.
     * It's the first step, when the ViewModel receives an action (intent)
     * The purpose is to call the proper use case for the required action.
     * The LiveData block will automatically execute when the LiveData becomes active,
     * and it will change its value when the use case calls emit()
     */
    override fun handle(action: MoviesAction) = liveData<MoviesResult> {
        when(action) {
            is MoviesAction.GetTopRatedMoviesAction -> topRatedMoviesUseCase.execute(this)
        }
    }

    /**
     * This is a [BaseViewModel] method we have to implement in every ViewModel.
     * It's the second step, when we receive the result of the use case
     * The purpose is to create and return a new view state
     */
    override fun reduce(result: MoviesResult): MoviesViewState {
        return when(result) {
            is MoviesResult.Loading -> internalViewState.copy(isLoading = true)
            is MoviesResult.Success -> internalViewState.copy(isLoading = false, movies = result.movies, error = null)
            is MoviesResult.Failure -> internalViewState.copy(isLoading = false, error = Throwable(result.errorMessage))
        }
    }
}