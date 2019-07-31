package com.juangm.domain.usecase

import androidx.lifecycle.LiveDataScope
import com.juangm.domain.repository.MoviesRepositoryContract
import com.juangm.domain.result.MoviesResult

class TopRatedMoviesUseCase(private val moviesRepository: MoviesRepositoryContract) {

    suspend fun getTopRatedMovies(scope: LiveDataScope<MoviesResult>) {
        scope.emit(MoviesResult.Loading)
        scope.emit(getTopRatedMoviesFromApi())
    }

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