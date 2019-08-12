package com.juangm.domain.usecase

import com.juangm.domain.repository.MoviesRepositoryContract

class LoadMoreTopRatedMoviesUseCase(private val moviesRepository: MoviesRepositoryContract): BaseMoviesUseCase() {

    override suspend fun getMoviesAsync() = moviesRepository.getTopRatedMoviesAsync(loadMore = true)
}