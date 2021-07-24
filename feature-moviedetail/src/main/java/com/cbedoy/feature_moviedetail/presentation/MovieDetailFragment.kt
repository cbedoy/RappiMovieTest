package com.cbedoy.feature_moviedetail.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.feature_moviedetail.R
import com.cbedoy.feature_moviedetail.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    /*
    private val args by navArgs<>()

    private val selectedMovie: Movie
        get() = args.movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailBinding.bind(view)
        with(binding) {
            imageView.load(selectedMovie.image) {
                crossfade(true)
            }
            titleView.text = selectedMovie.title
            overviewView.text = selectedMovie.overview
        }
    }*/
}