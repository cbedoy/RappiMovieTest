package com.cbedoy.feature_movielist.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.feature.movie_list.MovieListViewModel
import com.cbedoy.core.feature.movie_list.adapter.MovieListAdapter
import com.cbedoy.feature_movielist.R
import com.cbedoy.feature_movielist.databinding.FragmentAllMoviesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AllMovieListFragment : Fragment(R.layout.fragment_all_movies){

    private val viewModel by viewModel<MovieListViewModel>()

    private val topRatedMoviesAdapter = MovieListAdapter {
        navigateToMovieDetail(it)
    }

    private val popularMovieAdapter = MovieListAdapter {
        navigateToMovieDetail(it)
    }

    private fun navigateToMovieDetail(movie: Movie) {
        findNavController().navigate(
            AllMovieListFragmentDirections.actionAllMovieListFragmentToMovieDetailFragment(
                movie
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAllMoviesBinding.bind(view)
        with(binding.topRatedRecyclerView) {
            adapter = topRatedMoviesAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
        }
        with(binding.popularRecyclerView) {
            adapter = popularMovieAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
        }

        viewModel.topRatedFlow.observe(viewLifecycleOwner, {
            topRatedMoviesAdapter.submitList(it)
        })
        viewModel.popularFlow.observe(viewLifecycleOwner, {
            popularMovieAdapter.submitList(it)
        })
    }
}