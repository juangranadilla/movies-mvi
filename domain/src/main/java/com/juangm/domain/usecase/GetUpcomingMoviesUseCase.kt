package com.juangm.domain.usecase

import com.juangm.domain.repository.MoviesRepositoryContract
import com.juangm.domain.result.MoviesResult

class GetUpcomingMoviesUseCase(private val moviesRepository: MoviesRepositoryContract): BaseMoviesUseCase() {

    override suspend fun getMoviesAsync() = moviesRepository.getUpcomingMoviesAsync(loadMore = false)
    override val loadingResult = MoviesResult.Loading
}