package com.juangm.movies_mvi.domain.usecase

import androidx.lifecycle.LiveDataScope
import com.juangm.movies_mvi.data.repository.MoviesRepository
import com.juangm.movies_mvi.domain.result.MoviesResult

class TopRatedMoviesUseCase(private val moviesRepository: MoviesRepository) {

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