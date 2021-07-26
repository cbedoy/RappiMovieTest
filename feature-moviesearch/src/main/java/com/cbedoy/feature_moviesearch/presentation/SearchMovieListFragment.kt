package com.cbedoy.feature_moviesearch.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cbedoy.feature_moviesearch.R
import com.cbedoy.feature_moviesearch.SearchMovieIntent
import com.cbedoy.feature_moviesearch.SearchMovieState
import com.cbedoy.feature_moviesearch.SearchMovieViewModel
import com.cbedoy.feature_moviesearch.databinding.FragmentSearchMovieBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class SearchMovieListFragment : Fragment(R.layout.fragment_movielist){

    private val viewModel by viewModel<SearchMovieViewModel>()
    private val isLandscapeOrientation
        get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    private val gridLayoutManager: GridLayoutManager
        get() = GridLayoutManager(
            context, if (isLandscapeOrientation) 4 else 3
        )

    private val movieListAdapter = SearchMovieAdapter {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchMovieBinding.bind(view)

        with(binding) {
            with(recyclerView) {
                adapter = movieListAdapter
                layoutManager = gridLayoutManager
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect {
                handleState(it, binding)
            }
        }
    }

    private fun handleState(state: SearchMovieState, binding: FragmentSearchMovieBinding) {
        when(state) {
            is SearchMovieState.OnLoadResults -> {
                movieListAdapter.submitList(state.movies)
            }
            is SearchMovieState.OnShowLoader -> {
            }
            is SearchMovieState.Ilde -> {

            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie_menu, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView?
        searchView?.let {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    //Do your search
                    viewModel.performActionWithIntent(SearchMovieIntent.SearchMovieWithQuery(query))
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.performActionWithIntent(SearchMovieIntent.SearchMovieWithQuery(newText))
                    return false
                }
            })
        }
    }

}