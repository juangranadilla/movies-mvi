package com.juangm.domain.usecase

import com.juangm.domain.repository.MoviesRepositoryContract

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepositoryContract): BaseMoviesUseCase() {

    override suspend fun getMoviesAsync() = moviesRepository.getPopularMoviesAsync(loadMore = false)
}