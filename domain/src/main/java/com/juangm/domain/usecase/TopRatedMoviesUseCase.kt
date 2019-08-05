package com.juangm.domain.usecase

import androidx.lifecycle.LiveDataScope
import com.juangm.domain.repository.MoviesRepositoryContract
import com.juangm.domain.result.MoviesResult

class TopRatedMoviesUseCase(private val moviesRepository: MoviesRepositoryContract): BaseUseCase<MoviesResult>() {

    /**
     * Using the LiveDataScope for coroutines, we can use emit to call a suspend function and return the result,
     * or just emit a value directly, without calling a suspend function
     */
    override suspend fun execute(scope: LiveDataScope<MoviesResult>) {
        scope.emit(MoviesResult.Loading)
        scope.emit(getTopRatedMoviesFromApi())
    }

    /**
     * Here, we call the repository, receive a result, and return a MoviesResult, with the corresponding state
     */
    private suspend fun getTopRatedMoviesFromApi(): MoviesResult {
        try {
            var data = moviesRepository.getTopRatedMoviesAsync()
            data = data ?: emptyList()
            return MoviesResult.Success(data)
        } catch (throwable: Throwable) {
            return MoviesResult.Failure(throwable.localizedMessage)
        }
    }
}