package com.juangm.presentation.viewmodel

import com.juangm.domain.action.MoviesAction
import com.juangm.domain.models.Movie
import com.juangm.domain.repository.MoviesRepositoryContract
import com.juangm.domain.usecase.*
import com.juangm.presentation.state.MoviesViewState
import com.juangm.presentation.viewmodel.base.BaseMoviesViewModelTest
import com.juangm.presentation.viewmodel.util.TestObserver
import com.juangm.presentation.viewmodel.util.testObserver
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MoviesViewModelTest: BaseMoviesViewModelTest() {

    @Mock
    private lateinit var moviesRepository: MoviesRepositoryContract

    @Mock
    private lateinit var movies: List<Movie>

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var loadMorePopularMoviesUseCase: LoadMorePopularMoviesUseCase
    private lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
    private lateinit var loadMoreUpcomingMoviesUseCase: LoadMoreUpcomingMoviesUseCase
    private lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
    private lateinit var loadMoreTopRatedMoviesUseCase: LoadMoreTopRatedMoviesUseCase

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var viewStateLiveData: TestObserver<MoviesViewState>

    @Before
    fun setup() {
        getPopularMoviesUseCase = GetPopularMoviesUseCase(moviesRepository)
        loadMorePopularMoviesUseCase = LoadMorePopularMoviesUseCase(moviesRepository)
        getUpcomingMoviesUseCase = GetUpcomingMoviesUseCase(moviesRepository)
        loadMoreUpcomingMoviesUseCase = LoadMoreUpcomingMoviesUseCase(moviesRepository)
        getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(moviesRepository)
        loadMoreTopRatedMoviesUseCase = LoadMoreTopRatedMoviesUseCase(moviesRepository)

        moviesViewModel = MoviesViewModel(
            getTopRatedMoviesUseCase,
            loadMoreTopRatedMoviesUseCase,
            getPopularMoviesUseCase,
            loadMorePopularMoviesUseCase,
            getUpcomingMoviesUseCase,
            loadMoreUpcomingMoviesUseCase
        )

        viewStateLiveData = moviesViewModel.viewState.testObserver()
    }

    @Test
    fun `get popular movies successfully`() = runBlockingTest {
        `when`(moviesRepository.getPopularMoviesAsync(any())).thenReturn(movies)

        moviesViewModel.dispatch(MoviesAction.GetPopularMoviesAction)

        verify(moviesRepository).getPopularMoviesAsync(false)
        verifyNoMoreInteractions(moviesRepository)
        verifyGetMoviesSuccess(movies, viewStateLiveData)
    }

    @Test
    fun `get popular movies returning error`() = runBlockingTest {
        `when`(moviesRepository.getPopularMoviesAsync(any())).thenAnswer { throw Throwable("") }

        moviesViewModel.dispatch(MoviesAction.GetPopularMoviesAction)

        verify(moviesRepository).getPopularMoviesAsync(false)
        verifyNoMoreInteractions(moviesRepository)
        verifyGetMoviesError(viewStateLiveData)
    }

    @Test
    fun `get top rated movies successfully`() = runBlockingTest {
        `when`(moviesRepository.getTopRatedMoviesAsync(any())).thenReturn(movies)

        moviesViewModel.dispatch(MoviesAction.GetTopRatedMoviesAction)

        verify(moviesRepository).getTopRatedMoviesAsync(false)
        verifyNoMoreInteractions(moviesRepository)
        verifyGetMoviesSuccess(movies, viewStateLiveData)
    }

    @Test
    fun `get top rated movies returning error`() = runBlockingTest {
        `when`(moviesRepository.getTopRatedMoviesAsync(any())).thenAnswer { throw Throwable("") }

        moviesViewModel.dispatch(MoviesAction.GetTopRatedMoviesAction)

        verify(moviesRepository).getTopRatedMoviesAsync(false)
        verifyNoMoreInteractions(moviesRepository)
        verifyGetMoviesError(viewStateLiveData)
    }

    @Test
    fun `get upcoming movies successfully`() = runBlockingTest {
        `when`(moviesRepository.getUpcomingMoviesAsync(any())).thenReturn(movies)

        moviesViewModel.dispatch(MoviesAction.GetUpcomingMoviesAction)

        verify(moviesRepository).getUpcomingMoviesAsync(false)
        verifyNoMoreInteractions(moviesRepository)
        verifyGetMoviesSuccess(movies, viewStateLiveData)
    }

    @Test
    fun `get upcoming movies returning error`() = runBlockingTest {
        `when`(moviesRepository.getUpcomingMoviesAsync(any())).thenAnswer { throw Throwable("") }

        moviesViewModel.dispatch(MoviesAction.GetUpcomingMoviesAction)

        verify(moviesRepository).getUpcomingMoviesAsync(false)
        verifyNoMoreInteractions(moviesRepository)
        verifyGetMoviesError(viewStateLiveData)
    }

    @Test
    fun `load more popular movies successfully`() = runBlockingTest {
        `when`(moviesRepository.getPopularMoviesAsync(any())).thenReturn(movies)

        moviesViewModel.dispatch(MoviesAction.LoadMorePopularMoviesAction)

        verify(moviesRepository).getPopularMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verifyLoadMoreMoviesSuccess(movies, viewStateLiveData)
    }

    @Test
    fun `load more popular movies returning error`() = runBlockingTest {
        `when`(moviesRepository.getPopularMoviesAsync(any())).thenAnswer { throw Throwable("") }

        moviesViewModel.dispatch(MoviesAction.LoadMorePopularMoviesAction)

        verify(moviesRepository).getPopularMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verifyLoadMoreMoviesError(viewStateLiveData)
    }

    @Test
    fun `load more top rated movies successfully`() = runBlockingTest {
        `when`(moviesRepository.getTopRatedMoviesAsync(any())).thenReturn(movies)

        moviesViewModel.dispatch(MoviesAction.LoadMoreTopRatedMoviesAction)

        verify(moviesRepository).getTopRatedMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verifyLoadMoreMoviesSuccess(movies, viewStateLiveData)
    }

    @Test
    fun `load more top rated movies returning error`() = runBlockingTest {
        `when`(moviesRepository.getTopRatedMoviesAsync(any())).thenAnswer { throw Throwable("") }

        moviesViewModel.dispatch(MoviesAction.LoadMoreTopRatedMoviesAction)

        verify(moviesRepository).getTopRatedMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verifyLoadMoreMoviesError(viewStateLiveData)
    }

    @Test
    fun `load more upcoming movies successfully`() = runBlockingTest {
        `when`(moviesRepository.getUpcomingMoviesAsync(any())).thenReturn(movies)

        moviesViewModel.dispatch(MoviesAction.LoadMoreUpcomingMoviesAction)

        verify(moviesRepository).getUpcomingMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verifyLoadMoreMoviesSuccess(movies, viewStateLiveData)
    }

    @Test
    fun `load more upcoming movies returning error`() = runBlockingTest {
        `when`(moviesRepository.getUpcomingMoviesAsync(any())).thenAnswer { throw Throwable("") }

        moviesViewModel.dispatch(MoviesAction.LoadMoreUpcomingMoviesAction)

        verify(moviesRepository).getUpcomingMoviesAsync(true)
        verifyNoMoreInteractions(moviesRepository)
        verifyLoadMoreMoviesError(viewStateLiveData)
    }
}