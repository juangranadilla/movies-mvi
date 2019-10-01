package com.juangm.moviesmvi.ui.movies.list

import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.juangm.domain.action.MoviesAction
import com.juangm.domain.models.Movie

class UpcomingMoviesFragment : MoviesBaseFragment() {

    override fun getMovies() = moviesViewModel.dispatch(MoviesAction.GetUpcomingMoviesAction)

    override fun loadMoreMovies() = moviesViewModel.dispatch(MoviesAction.LoadMoreUpcomingMoviesAction)

    override fun showMovieDetails(movie: Movie, movieImage: ImageView) {
        val directions = UpcomingMoviesFragmentDirections.actionUpcomingMoviesFragmentToMovieDetailFragment(movie)
        val extras = FragmentNavigatorExtras(movieImage to movieImage.transitionName)
        findNavController().navigate(directions, extras)
    }
}
