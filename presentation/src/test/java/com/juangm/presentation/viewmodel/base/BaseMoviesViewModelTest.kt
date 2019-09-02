package com.juangm.presentation.viewmodel.base

import com.google.common.truth.Truth
import com.juangm.domain.models.Movie
import com.juangm.presentation.viewmodel.util.TestObserver
import com.juangm.presentation.state.MoviesViewState

abstract class BaseMoviesViewModelTest: BaseTest() {

    protected fun verifyGetMoviesSuccess(movies: List<Movie>, viewState: TestObserver<MoviesViewState>) {
        verifyLoadingState(viewState.observedValues.first())
        verifySuccessState(viewState.observedValues.last(), movies)
    }

    protected fun verifyGetMoviesError(viewState: TestObserver<MoviesViewState>) {
        verifyLoadingState(viewState.observedValues.first())
        verifyErrorState(viewState.observedValues.last())
    }

    protected fun verifyLoadMoreMoviesSuccess(movies: List<Movie>, viewState: TestObserver<MoviesViewState>) {
        verifyLoadingMoreState(viewState.observedValues.first())
        verifySuccessState(viewState.observedValues.last(), movies)
    }

    protected fun verifyLoadMoreMoviesError(viewState: TestObserver<MoviesViewState>) {
        verifyLoadingMoreState(viewState.observedValues.first())
        verifyErrorState(viewState.observedValues.last())
    }

    private fun verifyLoadingState(viewState: MoviesViewState?) {
        Truth.assertThat(viewState?.isLoading).isTrue()
        Truth.assertThat(viewState?.isLoadingMore).isFalse()
        Truth.assertThat(viewState?.error).isNull()
        Truth.assertThat(viewState?.movies).isEmpty()
    }

    private fun verifyLoadingMoreState(viewState: MoviesViewState?) {
        Truth.assertThat(viewState?.isLoading).isFalse()
        Truth.assertThat(viewState?.isLoadingMore).isTrue()
        Truth.assertThat(viewState?.error).isNull()
        Truth.assertThat(viewState?.movies).isEmpty()
    }

    private fun verifySuccessState(viewState: MoviesViewState?, movies: List<Movie>) {
        Truth.assertThat(viewState?.isLoading).isFalse()
        Truth.assertThat(viewState?.isLoadingMore).isFalse()
        Truth.assertThat(viewState?.error).isNull()
        Truth.assertThat(viewState?.movies).isEqualTo(movies)
    }

    private fun verifyErrorState(viewState: MoviesViewState?) {
        Truth.assertThat(viewState?.isLoading).isFalse()
        Truth.assertThat(viewState?.isLoadingMore).isFalse()
        Truth.assertThat(viewState?.error).isNotNull()
    }
}