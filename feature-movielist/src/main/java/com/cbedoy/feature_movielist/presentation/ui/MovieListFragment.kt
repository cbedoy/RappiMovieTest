package com.cbedoy.feature_movielist.presentation.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.cbedoy.feature_movielist.MovieListIntent
import com.cbedoy.feature_movielist.MovieListState
import com.cbedoy.feature_movielist.R
import com.cbedoy.feature_movielist.databinding.FragmentMovielistBinding
import com.cbedoy.feature_movielist.domain.FilterMovieOption
import com.cbedoy.feature_movielist.presentation.MovieListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    private val viewModel by viewModel<MovieListViewModel>()

    private val isLandscapeOrientation
        get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    private val gridLayoutManager: GridLayoutManager
        get() = GridLayoutManager(
            context, if (isLandscapeOrientation) 3 else 2
        )

    private val movieListAdapter = MovieListAdapter {

    }

    private val binding by lazy {
        FragmentMovielistBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.recyclerView) {
            adapter = movieListAdapter
            layoutManager = gridLayoutManager
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.performActionWithIntent(MovieListIntent.LoadMoviesSortedBy(FilterMovieOption.TopRated))
        }

        lifecycleScope.launch {
            viewModel.state.collect { handleState(it) }
            viewModel.asyncMoviesState.collect { handleState(it) }
        }
    }

    private fun handleState(state: MovieListState) {
        when(state) {
            is MovieListState.Ilde -> { }
            is MovieListState.OnLoadMovies -> {
                movieListAdapter.submitList(state.movies)
            }
            is MovieListState.ShowLoader -> {
                binding.swipeRefreshLayout.isRefreshing = state.isVisible
            }
        }
    }
}