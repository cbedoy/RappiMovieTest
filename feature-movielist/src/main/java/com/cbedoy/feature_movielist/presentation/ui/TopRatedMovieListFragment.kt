package com.cbedoy.feature_movielist.presentation.ui

import androidx.navigation.fragment.findNavController
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.feature_movielist.domain.FilterMovieOption
import com.cbedoy.feature_movielist.presentation.ui.base.MovieListFragment

class TopRatedMovieListFragment : MovieListFragment(){
    override val filter: FilterMovieOption
        get() = FilterMovieOption.TopRated

    override val onSelectedMovie: (Movie) -> Unit
        get() = {
            findNavController().navigate(
                TopRatedMovieListFragmentDirections.actionFragmentPopularTopRatedToFragmentMovieDetail(
                    it
                )
            )
        }
}