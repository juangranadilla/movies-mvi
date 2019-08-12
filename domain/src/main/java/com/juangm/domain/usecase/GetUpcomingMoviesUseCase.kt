package com.juangm.domain.usecase

import com.juangm.domain.repository.MoviesRepositoryContract

class GetUpcomingMoviesUseCase(private val moviesRepository: MoviesRepositoryContract): BaseMoviesUseCase() {

    override suspend fun getMoviesAsync() = moviesRepository.getUpcomingMoviesAsync(loadMore = false)
}