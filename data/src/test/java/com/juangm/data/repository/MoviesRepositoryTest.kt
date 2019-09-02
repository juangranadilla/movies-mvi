package com.juangm.data.repository

import com.juangm.data.repository.base.BaseTest
import com.juangm.data.source.remote.MoviesRemoteSourceContract
import com.juangm.data.source.remote.api.MoviesResponse
import com.juangm.domain.models.Movie
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class MoviesRepositoryTest: BaseTest() {

    @Mock
    private lateinit var moviesRemoteSource: MoviesRemoteSourceContract

    private lateinit var moviesRepository: MoviesRepository
    private lateinit var moviesResponse: MoviesResponse

    @Before
    fun setup() {
        moviesRepository = MoviesRepository(moviesRemoteSource)

        val actualPage = 1
        val movies = listOf(Movie())
        moviesResponse = mock {
            on { page } doReturn actualPage
            on { results } doReturn movies
        }
    }

    @Test
    fun `get popular movies from remote source`() = runBlockingTest {
        `when`(moviesRemoteSource.getPopularMoviesFromApi(any())).thenReturn(moviesResponse)

        moviesRepository.getPopularMoviesAsync(false)

        verify(moviesRemoteSource).getPopularMoviesFromApi()
        verifyNoMoreInteractions(moviesRemoteSource)
    }

    @Test
    fun `get top movies from remote source`() = runBlockingTest {
        `when`(moviesRemoteSource.getTopRatedMoviesFromApi(any())).thenReturn(moviesResponse)

        moviesRepository.getTopRatedMoviesAsync(false)

        verify(moviesRemoteSource).getTopRatedMoviesFromApi()
        verifyNoMoreInteractions(moviesRemoteSource)
    }

    @Test
    fun `get upcoming movies from remote source`() = runBlockingTest {
        `when`(moviesRemoteSource.getUpcomingMoviesFromApi(any())).thenReturn(moviesResponse)

        moviesRepository.getUpcomingMoviesAsync(false)

        verify(moviesRemoteSource).getUpcomingMoviesFromApi()
        verifyNoMoreInteractions(moviesRemoteSource)
    }
}