package com.juangm.moviesmvi.ui.movies.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.juangm.domain.models.Movie
import com.juangm.moviesmvi.R
import com.juangm.moviesmvi.constants.TMDB_BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_movie.view.*
import timber.log.Timber

class MoviesAdapter(
    private val movieClickListener: MovieClickListener
): ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        Timber.d("Binding view holder at position $position --> id: ${movie.id}, title: ${movie.title}")
        holder.bind(movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                movieImage.load(TMDB_BASE_IMAGE_URL + movie.posterPath) {
                    crossfade(true)
                    error(R.drawable.ic_movie_image_error_background)
                    transformations(RoundedCornersTransformation(10f))
                }

                setOnClickListener {
                    movieImage.transitionName = context.getString(R.string.movie_image_transition, adapterPosition)
                    movieClickListener.onMovieClick(movie, movieImage, adapterPosition)
                }
            }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}