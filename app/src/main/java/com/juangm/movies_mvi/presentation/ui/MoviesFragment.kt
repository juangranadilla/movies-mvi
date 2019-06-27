package com.juangm.movies_mvi.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.juangm.movies_mvi.R
import com.juangm.movies_mvi.domain.action.MoviesAction
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MoviesFragment : Fragment() {

    private val moviesViewModel by viewModel<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel.viewState.observe(this, Observer {
            render(it)
        })

        moviesViewModel.dispatch(MoviesAction.GetTopRatedMoviesAction)
    }

    private fun render(state: MoviesViewState) {
        //TODO show loading, movies list or error

        Timber.i("isLoading: ${state.isLoading}")
        Timber.i("movies: ${state.movies.size}")
        state.error?.let {
            Timber.i("error: $it")
        }
    }
}
