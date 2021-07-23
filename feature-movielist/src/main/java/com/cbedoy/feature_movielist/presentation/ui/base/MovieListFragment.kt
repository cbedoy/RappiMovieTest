package com.cbedoy.feature_movielist.presentation.ui.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.cbedoy.feature_movielist.MovieListIntent
import com.cbedoy.feature_movielist.MovieListState
import com.cbedoy.feature_movielist.R
import com.cbedoy.feature_movielist.databinding.FragmentMovielistBinding
import com.cbedoy.feature_movielist.domain.FilterMovieOption
import com.cbedoy.feature_movielist.presentation.MovieListViewModel
import com.cbedoy.feature_movielist.presentation.ui.adapter.MovieListAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class MovieListFragment : Fragment(R.layout.fragment_movielist) {

    abstract val filter: FilterMovieOption

    private val viewModel by viewModel<MovieListViewModel>()
    private val isLandscapeOrientation
        get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    private val gridLayoutManager: GridLayoutManager
        get() = GridLayoutManager(
            context, if (isLandscapeOrientation) 4 else 3
        )

    private val movieListAdapter = MovieListAdapter {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovielistBinding.bind(view)

        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.performActionWithIntent(MovieListIntent.LoadMoviesSortedBy(filter))
            }

            with(recyclerView) {
                adapter = movieListAdapter
                layoutManager = gridLayoutManager
            }

            lifecycleScope.launch {
                viewModel.state.collect { state -> handleState(state, binding) }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.performActionWithIntent(MovieListIntent.LoadMoviesSortedBy(filter))
    }

    private fun handleState(state: MovieListState, binding: FragmentMovielistBinding) {
        when(state) {
            is MovieListState.Ilde -> { }
            is MovieListState.OnLoadMovies -> {
                movieListAdapter.submitList(state.movies)
            }
            is MovieListState.ShowLoader -> {
                //binding.swipeRefreshLayout.isRefreshing = state.isVisible
            }
        }
    }
}