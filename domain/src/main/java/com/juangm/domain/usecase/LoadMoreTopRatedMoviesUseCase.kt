package com.juangm.domain.usecase

import com.juangm.domain.repository.MoviesRepositoryContract
import com.juangm.domain.result.MoviesResult

class LoadMoreTopRatedMoviesUseCase(private val moviesRepository: MoviesRepositoryContract): BaseMoviesUseCase() {

    override suspend fun getMoviesAsync() = moviesRepository.getTopRatedMoviesAsync(loadMore = true)
    override val loadingResult = MoviesResult.LoadingMore
}