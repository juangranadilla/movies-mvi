package com.juangm.domain.usecase

import androidx.lifecycle.LiveDataScope
import com.juangm.domain.models.Movie
import com.juangm.domain.result.MoviesResult

abstract class BaseMoviesUseCase: BaseUseCase<MoviesResult>() {

    abstract suspend fun getMoviesAsync(): List<Movie>?
    abstract val loadingResult: MoviesResult

    /**
     * Using the LiveDataScope for coroutines, we can use emit to call a suspend function
     * and return the result, or just emit a value directly, without calling a suspend function
     */
    override suspend fun execute(scope: LiveDataScope<MoviesResult>) {
        scope.emit(loadingResult)
        scope.emit(getMovies())
    }

    /**
     * Here, we call the repository, receive a result, and return a MoviesResult,
     * with the corresponding state
     */
    private suspend fun getMovies(): MoviesResult {
        try {
            var data = getMoviesAsync()
            data = data ?: emptyList()
            return MoviesResult.Success(data)
        } catch (throwable: Throwable) {
            return MoviesResult.Failure(throwable.localizedMessage)
        }
    }
}