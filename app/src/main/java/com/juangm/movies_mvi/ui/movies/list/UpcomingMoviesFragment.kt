package com.juangm.movies_mvi.ui.movies.list

import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.juangm.domain.action.MoviesAction
import com.juangm.domain.models.Movie

class UpcomingMoviesFragment : MoviesBaseFragment() {

    override fun getMovies() = moviesViewModel.dispatch(MoviesAction.GetUpcomingMoviesAction)

    override fun loadMoreMovies() = moviesViewModel.dispatch(MoviesAction.LoadMoreUpcomingMoviesAction)

    override fun showMovieDetails(movie: Movie, movieImage: ImageView, position: Int) {
        val directions = UpcomingMoviesFragmentDirections.actionUpcomingMoviesFragmentToMovieDetailFragment(movie, position)
        val extras = FragmentNavigatorExtras(movieImage to movieImage.transitionName)
        findNavController().navigate(directions, extras)
    }
}