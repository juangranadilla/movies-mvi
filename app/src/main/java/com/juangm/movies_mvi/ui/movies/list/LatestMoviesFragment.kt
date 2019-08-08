package com.juangm.movies_mvi.ui.movies.list

import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.juangm.domain.models.Movie
import timber.log.Timber

class LatestMoviesFragment : MoviesBaseFragment() {

    override fun getMovies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMoreMovies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMovieDetails(movie: Movie, movieImage: ImageView, position: Int) {
        Timber.i("Showing detail for movie ${movie.id} with name: ${movie.title} at position $position")
        val directions = LatestMoviesFragmentDirections.actionLatestMoviesFragmentToMovieDetailFragment(movie, position)
        val extras = FragmentNavigatorExtras(movieImage to movieImage.transitionName)
        findNavController().navigate(directions, extras)
    }
}
