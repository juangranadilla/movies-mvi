package com.juangm.domain.usecase

import androidx.lifecycle.LiveDataScope
import com.juangm.domain.models.Movie
import com.juangm.domain.repository.MoviesRepositoryContract
import com.juangm.domain.result.MoviesResult
import com.juangm.domain.usecase.base.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class GetUpcomingMoviesUseCaseTest: BaseTest() {

    @Mock
    private lateinit var moviesRepository: MoviesRepositoryContract

    @Mock
    private lateinit var movies: List<Movie>

    @Mock
    private lateinit var liveDataScope: LiveDataScope<MoviesResult>

    private lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase

    @Before
    fun setup() {
        getUpcomingMoviesUseCase = GetUpcomingMoviesUseCase(moviesRepository)
    }

    @Test
    fun `get movies with a success result`() = runBlockingTest {
        `when`(moviesRepository.getUpcomingMoviesAsync(false)).thenReturn(movies)

        getUpcomingMoviesUseCase.execute(liveDataScope)

        verify(moviesRepository).getUpcomingMoviesAsync(false)
        verify(liveDataScope).emit(getUpcomingMoviesUseCase.loadingResult)
        verify(liveDataScope).emit(MoviesResult.Success(movies))
    }

    @Test
    fun `get movies with a failure result`() = runBlockingTest {
        val errorMessage = "Error getting movies"
        `when`(moviesRepository.getUpcomingMoviesAsync(false))
            .thenAnswer {
                throw Throwable(errorMessage)
            }

        getUpcomingMoviesUseCase.execute(liveDataScope)

        verify(moviesRepository).getUpcomingMoviesAsync(false)
        verify(liveDataScope).emit(getUpcomingMoviesUseCase.loadingResult)
        verify(liveDataScope).emit(MoviesResult.Failure(errorMessage))
    }
}