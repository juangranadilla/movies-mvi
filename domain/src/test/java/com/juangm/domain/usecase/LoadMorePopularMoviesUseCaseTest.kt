package com.juangm.domain.usecase

import androidx.lifecycle.LiveDataScope
import com.juangm.domain.models.Movie
import com.juangm.domain.repository.MoviesRepositoryContract
import com.juangm.domain.result.MoviesResult
import com.juangm.domain.usecase.base.BaseTest
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class LoadMorePopularMoviesUseCaseTest: BaseTest() {

    @Mock
    private lateinit var moviesRepository: MoviesRepositoryContract

    @Mock
    private lateinit var movies: List<Movie>

    @Mock
    private lateinit var liveDataScope: LiveDataScope<MoviesResult>

    private lateinit var loadMorePopularMoviesUseCase: LoadMorePopularMoviesUseCase

    @Before
    fun setup() {
        loadMorePopularMoviesUseCase = LoadMorePopularMoviesUseCase(moviesRepository)
    }

    @Test
    fun `get movies with a success result`() = runBlockingTest {
        `when`(moviesRepository.getPopularMoviesAsync(true)).thenReturn(movies)

        loadMorePopularMoviesUseCase.execute(liveDataScope)

        verify(moviesRepository).getPopularMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verify(liveDataScope).emit(loadMorePopularMoviesUseCase.loadingResult)
        verify(liveDataScope).emit(MoviesResult.Success(movies))
    }

    @Test
    fun `get movies with a failure result`() = runBlockingTest {
        val errorMessage = "Error getting movies"
        `when`(moviesRepository.getPopularMoviesAsync(true))
            .thenAnswer {
                throw Throwable(errorMessage)
            }

        loadMorePopularMoviesUseCase.execute(liveDataScope)

        verify(moviesRepository).getPopularMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verify(liveDataScope).emit(loadMorePopularMoviesUseCase.loadingResult)
        verify(liveDataScope).emit(MoviesResult.Failure(errorMessage))
    }
}