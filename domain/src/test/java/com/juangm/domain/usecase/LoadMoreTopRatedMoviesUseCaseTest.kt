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
class LoadMoreTopRatedMoviesUseCaseTest: BaseTest() {

    @Mock
    private lateinit var moviesRepository: MoviesRepositoryContract

    @Mock
    private lateinit var movies: List<Movie>

    @Mock
    private lateinit var liveDataScope: LiveDataScope<MoviesResult>

    private lateinit var loadMoreTopRatedMoviesUseCase: LoadMoreTopRatedMoviesUseCase

    @Before
    fun setup() {
        loadMoreTopRatedMoviesUseCase = LoadMoreTopRatedMoviesUseCase(moviesRepository)
    }

    @Test
    fun `get movies with a success result`() = runBlockingTest {
        `when`(moviesRepository.getTopRatedMoviesAsync(true)).thenReturn(movies)

        loadMoreTopRatedMoviesUseCase.execute(liveDataScope)

        verify(moviesRepository).getTopRatedMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verify(liveDataScope).emit(loadMoreTopRatedMoviesUseCase.loadingResult)
        verify(liveDataScope).emit(MoviesResult.Success(movies))
    }

    @Test
    fun `get movies with a failure result`() = runBlockingTest {
        val errorMessage = "Error getting movies"
        `when`(moviesRepository.getTopRatedMoviesAsync(true))
            .thenAnswer {
                throw Throwable(errorMessage)
            }

        loadMoreTopRatedMoviesUseCase.execute(liveDataScope)

        verify(moviesRepository).getTopRatedMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verify(liveDataScope).emit(loadMoreTopRatedMoviesUseCase.loadingResult)
        verify(liveDataScope).emit(MoviesResult.Failure(errorMessage))
    }
}