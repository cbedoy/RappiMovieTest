package com.cbedoy.feature_movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cbedoy.feature_movielist.databinding.FragmentMovielistBinding

class MovieListFragment : Fragment(R.layout.fragment_movielist) {

    private val movieListAdapter = MovieListAdapter {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovielistBinding.bind(view)
        with(binding.recyclerView) {
            adapter = movieListAdapter

        }
    }
}