package com.juangm.presentation.viewmodel

import androidx.lifecycle.liveData
import com.juangm.domain.action.MoviesAction
import com.juangm.domain.result.MoviesResult
import com.juangm.domain.usecase.GetTopRatedMoviesUseCase
import com.juangm.domain.usecase.LoadMoreTopRatedMoviesUseCase
import com.juangm.presentation.state.MoviesViewState

class MoviesViewModel(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val loadMoreTopRatedMoviesUseCase: LoadMoreTopRatedMoviesUseCase
): BaseViewModel<MoviesViewState, MoviesAction, MoviesResult>() {

    /**
     * This is the last view state. We initialize it here, and we will use it in the reduce method,
     * with the data class copy function, to create the new state, depending on the received result
     */
    override var internalViewState = MoviesViewState()

    /**
     * This is a [BaseViewModel] method we have to implement in every ViewModel.
     * It's the first step, when the ViewModel receives an action (intent)
     * The purpose is to call the proper use case for the required action.
     * The LiveData block will automatically execute when the LiveData becomes active,
     * and it will change its value when the use case calls emit()
     */
    override fun handle(action: MoviesAction) = liveData<MoviesResult> {
        when(action) {
            is MoviesAction.GetTopRatedMoviesAction ->
                if(internalViewState.movies.isEmpty())
                    getTopRatedMoviesUseCase.execute(this)
            is MoviesAction.LoadMoreTopRatedMoviesAction -> loadMoreTopRatedMoviesUseCase.execute(this)
        }
    }

    /**
     * This is a [BaseViewModel] method we have to implement in every ViewModel.
     * It's the second step, when we receive the result of the use case
     * The purpose is to create and return a new view state
     */
    override fun reduce(result: MoviesResult): MoviesViewState {
        return when(result) {
            is MoviesResult.Loading -> newState(internalViewState.copy(isLoading = true))
            is MoviesResult.LoadingMore -> newState(internalViewState.copy(isLoading = false, isLoadingMore = true))
            is MoviesResult.Success -> newState(internalViewState.copy(
                isLoading = false,
                isLoadingMore = false,
                movies = result.movies,
                error = null)
            )
            is MoviesResult.Failure -> newState(internalViewState.copy(
                isLoading = false,
                isLoadingMore = false,
                error = Throwable(result.errorMessage))
            )
        }
    }
}