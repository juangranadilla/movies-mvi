package com.juangm.domain.usecase

import com.juangm.domain.repository.MoviesRepositoryContract
import com.juangm.domain.result.MoviesResult

class LoadMorePopularMoviesUseCase(private val moviesRepository: MoviesRepositoryContract): BaseMoviesUseCase() {

    override suspend fun getMoviesAsync() = moviesRepository.getPopularMoviesAsync(loadMore = true)
    override val loadingResult = MoviesResult.LoadingMore
}