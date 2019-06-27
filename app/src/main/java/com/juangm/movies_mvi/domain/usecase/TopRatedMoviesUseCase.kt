package com.juangm.movies_mvi.domain.usecase

import com.juangm.movies_mvi.data.repository.MoviesRepository

class TopRatedMoviesUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun getTopRatedMovies() = moviesRepository.getTopRatedMoviesAsync()
}