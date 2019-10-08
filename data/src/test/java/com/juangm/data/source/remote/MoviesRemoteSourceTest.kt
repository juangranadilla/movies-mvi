package com.juangm.data.source.remote

import com.juangm.data.repository.base.BaseTest
import com.juangm.data.source.remote.api.MoviesResponse
import com.juangm.data.source.remote.api.MoviesService
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
import retrofit2.Response

@ExperimentalCoroutinesApi
class MoviesRemoteSourceTest: BaseTest() {

    @Mock
    private lateinit var moviesService: MoviesService

    @Mock
    private lateinit var moviesResponse: Response<MoviesResponse>

    private lateinit var moviesRemoteSource: MoviesRemoteSource

    private val page = 1

    @Before
    fun setup() {
        moviesRemoteSource = MoviesRemoteSource(moviesService)
        moviesResponse = mock {
            on { body() } doReturn MoviesResponse()
        }
    }

    @Test
    fun `get popular movies from API`() = runBlockingTest {
        `when`(moviesService.getPopularMoviesAsync(page = page))
            .thenReturn(moviesResponse)

        moviesRemoteSource.getPopularMoviesFromApi(page)

        verify(moviesService).getPopularMoviesAsync(page = page)
        verifyNoMoreInteractions(moviesService)
    }

    @Test
    fun `get top rated movies from API`() = runBlockingTest {
        `when`(moviesService.getTopRatedMoviesAsync(page = page))
            .thenReturn(moviesResponse)

        moviesRemoteSource.getTopRatedMoviesFromApi(page)

        verify(moviesService).getTopRatedMoviesAsync(page = page)
        verifyNoMoreInteractions(moviesService)
    }

    @Test
    fun `get upcoming movies from API`() = runBlockingTest {
        `when`(moviesService.getUpcomingMoviesAsync(page = page))
            .thenReturn(moviesResponse)

        moviesRemoteSource.getUpcomingMoviesFromApi(page)

        verify(moviesService).getUpcomingMoviesAsync(page = page)
        verifyNoMoreInteractions(moviesService)
    }
}