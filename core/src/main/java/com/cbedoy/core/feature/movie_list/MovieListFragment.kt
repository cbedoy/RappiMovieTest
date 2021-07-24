package com.cbedoy.core.feature.movie_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.cbedoy.core.R
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.datasource.FilterMovieOption
import com.cbedoy.core.databinding.FragmentMovielistBinding
import com.cbedoy.core.feature.movie_list.adapter.MovieListAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class MovieListFragment : Fragment(R.layout.fragment_movielist) {

    abstract val filter: FilterMovieOption
    abstract val onSelectedMovie: (Movie) -> Unit

    private val viewModel by viewModel<MovieListViewModel>()
    private val isLandscapeOrientation
        get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    private val gridLayoutManager: GridLayoutManager
        get() = GridLayoutManager(
                context, if (isLandscapeOrientation) 4 else 3
        )

    private val movieListAdapter = MovieListAdapter {
        onSelectedMovie(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovielistBinding.bind(view)

        with(binding) {
            with(recyclerView) {
                adapter = movieListAdapter
                layoutManager = gridLayoutManager
            }
        }

        lifecycleScope.launch {
            if (filter == FilterMovieOption.TopRated) {
                collectFlow(viewModel.topRatedFlow)
            }else {
                collectFlow(viewModel.popularFlow)
            }
        }
    }

    private suspend fun collectFlow(flow: Flow<PagingData<Movie>>) {
        flow.collectLatest {
            movieListAdapter.submitData(it)
        }
    }
}