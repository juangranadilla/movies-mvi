package com.juangm.moviesmvi.ui.movies.list

import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.juangm.domain.action.MoviesAction
import com.juangm.domain.models.Movie

class PopularMoviesFragment : MoviesBaseFragment() {

    override fun getMovies() = moviesViewModel.dispatch(MoviesAction.GetPopularMoviesAction)

    override fun loadMoreMovies() = moviesViewModel.dispatch(MoviesAction.LoadMorePopularMoviesAction)

    override fun showMovieDetails(movie: Movie, movieImage: ImageView) {
        val directions = PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailFragment(movie)
        val extras = FragmentNavigatorExtras(movieImage to movieImage.transitionName)
        findNavController().navigate(directions, extras)
    }
}
