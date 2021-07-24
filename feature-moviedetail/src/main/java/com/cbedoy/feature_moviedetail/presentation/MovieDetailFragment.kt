package com.cbedoy.feature_moviedetail.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.feature_moviedetail.R
import com.cbedoy.feature_moviedetail.databinding.FragmentMovieDetailBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel by inject<MovieDetailViewModel>()
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val selectedMovie: Movie
        get() = args.movie

    private val previewVideoAdapter = PreviewVideoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailBinding.bind(view)
        with(binding) {
            imageView.load(selectedMovie.image) {
                crossfade(true)
            }
            titleView.text = selectedMovie.title
            overviewView.text = selectedMovie.overview
            with(binding.recyclerView) {
                adapter = previewVideoAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect { handleState(it, binding) }
        }
        viewModel.performActionWithIntent(MovieDetailIntent.LoadDetails(selectedMovie.uuid))
    }

    private fun handleState(state: MovieDetailState, binding: FragmentMovieDetailBinding) {
        when(state) {
            is MovieDetailState.OnLoadedPreviewDetails -> {
                previewVideoAdapter.dataSource = state.previews
            }
            is MovieDetailState.Ilde -> {

            }
        }
    }
}