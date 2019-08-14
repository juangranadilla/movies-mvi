package com.juangm.movies_mvi.ui.movies.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.juangm.domain.models.Movie
import com.juangm.movies_mvi.R
import com.juangm.movies_mvi.constants.TMDB_BASE_IMAGE_URL
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = args.movie
        val position = args.position
        renderMovieData(movie, position)
    }

    private fun renderMovieData(movie: Movie, position: Int) {
        movie_title.text = movie.title
        movie_description.text = movie.overview
        movie_date.text = movie.releaseDate
        movie_image.transitionName = getString(R.string.movie_image_transition, position)

        movie_image_background.load(TMDB_BASE_IMAGE_URL + movie.posterPath) {
            crossfade(true)
            context?.run { transformations(BlurTransformation(this)) }
        }

        movie_image.load(TMDB_BASE_IMAGE_URL + movie.posterPath) {
            placeholder(R.drawable.ic_movie_image_error_background)
            error(R.drawable.ic_movie_image_error_background)
            transformations(RoundedCornersTransformation(10f))
        }
    }
}
