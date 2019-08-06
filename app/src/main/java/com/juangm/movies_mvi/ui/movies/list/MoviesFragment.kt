package com.juangm.movies_mvi.ui.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.juangm.domain.action.MoviesAction
import com.juangm.domain.models.Movie
import com.juangm.movies_mvi.R
import com.juangm.movies_mvi.ui.utils.GridMarginItemDecoration
import com.juangm.presentation.state.MoviesViewState
import com.juangm.presentation.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MoviesFragment : Fragment(), MovieClickListener {

    private val moviesViewModel by viewModel<MoviesViewModel>()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerAdapter()
        observeMovies()

        /**
         * This is the intent, an intention to perform an action. In this case, it's called automatically on start,
         * but usually, this will be an action performed by an user, like clicking on a button.
         * This will tell the ViewModel the action we want to dispatch
         */
        getTopRatedMoviesAction()
    }

    private fun setRecyclerAdapter() {
        moviesAdapter = MoviesAdapter(this)
        movies_recycler.apply {
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(GridMarginItemDecoration(10))
            adapter = moviesAdapter
        }
    }

    /**
     * We observe for any change in the view state, in order to render it
     */
    private fun observeMovies() {
        moviesViewModel.viewState.observe(this, Observer { moviesViewState ->
            render(moviesViewState)
        })
    }

    private fun getTopRatedMoviesAction() {
        moviesViewModel.dispatch(MoviesAction.GetTopRatedMoviesAction)
    }

    /**
     * This is the only function we will call to update the view,
     * and only when the view state we are observing in the ViewModel has changed,
     * due to a use intent or action.
     */
    private fun render(state: MoviesViewState) {
        renderLoadingState(state.isLoading)
        renderMoviesState(state.movies)
        renderErrorState(state.error)
    }

    private fun renderLoadingState(isLoading: Boolean) {
        Timber.i("isLoading: $isLoading")
        if(isLoading)
            movies_progress_bar.visibility = View.VISIBLE
        else
            movies_progress_bar.visibility = View.GONE
    }

    private fun renderMoviesState(movies: List<Movie>) {
        Timber.i("movies: ${movies.size}")
        moviesAdapter.submitList(movies)
    }

    private fun renderErrorState(error: Throwable?) {
        error?.let {
            Timber.i("error: $it")
            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun onMovieClick(movie: Movie, movieImage: ImageView, position: Int) {
        Timber.i("Showing detail for movie ${movie.id} with name: ${movie.originalTitle} at position $position")
        val directions = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movie, position)
        val extras = FragmentNavigatorExtras(movieImage to movieImage.transitionName)
        findNavController().navigate(directions, extras)
    }
}
