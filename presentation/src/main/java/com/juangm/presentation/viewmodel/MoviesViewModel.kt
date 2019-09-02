package com.juangm.presentation.viewmodel

import androidx.lifecycle.liveData
import com.juangm.domain.action.MoviesAction
import com.juangm.domain.result.MoviesResult
import com.juangm.domain.usecase.*
import com.juangm.presentation.state.MoviesViewState
import timber.log.Timber

class MoviesViewModel(
    private val getTopRatedTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val loadMoreTopRatedTopRatedMoviesUseCase: LoadMoreTopRatedMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val loadMorePopularMoviesUseCase: LoadMorePopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val loadMoreUpcomingMoviesUseCase: LoadMoreUpcomingMoviesUseCase
): BaseViewModel<MoviesViewState, MoviesAction, MoviesResult>(MoviesViewState()) {

    /**
     * This is a [BaseViewModel] method we have to implement in every ViewModel.
     * It's the first step, when the ViewModel receives an action (intent)
     * The purpose is to call the proper use case for the required action.
     * The LiveData block will automatically execute when the LiveData becomes active (just after an action),
     * and it will change its value when the use case calls emit()
     */
    override fun handle(action: MoviesAction) = liveData<MoviesResult> {
        Timber.i("Handle: $action")
        when(action) {
            is MoviesAction.GetPopularMoviesAction -> getPopularMoviesUseCase.execute(this)
            is MoviesAction.LoadMorePopularMoviesAction -> loadMorePopularMoviesUseCase.execute(this)
            is MoviesAction.GetTopRatedMoviesAction -> getTopRatedTopRatedMoviesUseCase.execute(this)
            is MoviesAction.LoadMoreTopRatedMoviesAction -> loadMoreTopRatedTopRatedMoviesUseCase.execute(this)
            is MoviesAction.GetUpcomingMoviesAction -> getUpcomingMoviesUseCase.execute(this)
            is MoviesAction.LoadMoreUpcomingMoviesAction -> loadMoreUpcomingMoviesUseCase.execute(this)
        }
    }

    /**
     * This is a [BaseViewModel] method we have to implement in every ViewModel.
     * It's the second step, when we receive the result of the use case and the last view state.
     * The purpose is to create and return the new view state
     */
    override fun reduce(currentViewState: MoviesViewState, result: MoviesResult): MoviesViewState {
        Timber.i("currentViewState: $currentViewState")
        Timber.i("Reduce: $result")
        return when(result) {
            is MoviesResult.Loading -> currentViewState.copy(isLoading = true)
            is MoviesResult.LoadingMore -> currentViewState.copy(isLoading = false, isLoadingMore = true)
            is MoviesResult.Success -> currentViewState.copy(
                isLoading = false,
                isLoadingMore = false,
                movies = result.movies,
                error = null
            )
            is MoviesResult.Failure -> currentViewState.copy(
                isLoading = false,
                isLoadingMore = false,
                error = Throwable(result.errorMessage)
            )
        }
    }
}