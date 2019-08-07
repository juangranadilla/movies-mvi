package com.juangm.domain.usecase

import com.juangm.domain.repository.MoviesRepositoryContract

class LoadMoreTopRatedMoviesUseCase(private val moviesRepository: MoviesRepositoryContract): BaseTopRatedMoviesUseCase() {

    override suspend fun getTopRatedMoviesAsync() = moviesRepository.getTopRatedMoviesAsync(loadMore = true)
}