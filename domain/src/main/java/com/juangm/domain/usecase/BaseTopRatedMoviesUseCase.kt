package com.juangm.domain.usecase

import androidx.lifecycle.LiveDataScope
import com.juangm.domain.models.Movie
import com.juangm.domain.result.MoviesResult

abstract class BaseTopRatedMoviesUseCase: BaseUseCase<MoviesResult>() {

    abstract suspend fun getTopRatedMoviesAsync(): List<Movie>?

    /**
     * Using the LiveDataScope for coroutines, we can use emit to call a suspend function and return the result,
     * or just emit a value directly, without calling a suspend function
     */
    override suspend fun execute(scope: LiveDataScope<MoviesResult>) {
        scope.emit(MoviesResult.LoadingMore)
        scope.emit(getMoreTopRatedMovies())
    }

    /**
     * Here, we call the repository, receive a result, and return a MoviesResult, with the corresponding state
     */
    private suspend fun getMoreTopRatedMovies(): MoviesResult {
        try {
            var data = getTopRatedMoviesAsync()
            data = data ?: emptyList()
            return MoviesResult.Success(data)
        } catch (throwable: Throwable) {
            return MoviesResult.Failure(throwable.localizedMessage)
        }
    }
}