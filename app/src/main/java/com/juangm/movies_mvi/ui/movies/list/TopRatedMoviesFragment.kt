package com.juangm.movies_mvi.ui.movies.list

import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.juangm.domain.action.MoviesAction
import com.juangm.domain.models.Movie
import timber.log.Timber

class TopRatedMoviesFragment : MoviesBaseFragment() {

    override fun getMovies() = moviesViewModel.dispatch(MoviesAction.GetTopRatedMoviesAction)

    override fun loadMoreMovies() = moviesViewModel.dispatch(MoviesAction.LoadMoreTopRatedMoviesAction)

    override fun showMovieDetails(movie: Movie, movieImage: ImageView, position: Int) {
        Timber.i("Showing detail for movie ${movie.id} with name: ${movie.title} at position $position")
        val directions = TopRatedMoviesFragmentDirections.actionTopRatedMoviesFragmentToMovieDetailFragment(movie, position)
        val extras = FragmentNavigatorExtras(movieImage to movieImage.transitionName)
        findNavController().navigate(directions, extras)
    }
}
