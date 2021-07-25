package com.cbedoy.feature_movielist.ui

import androidx.navigation.fragment.findNavController
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.datasource.FilterMovieOption
import com.cbedoy.core.feature.movie_list.MovieListFragment

class PopularMovieListFragment : MovieListFragment() {
    override val filter: FilterMovieOption
        get() = FilterMovieOption.Popularity

    override val onSelectedMovie: (Movie) -> Unit
        get() = {
            findNavController().navigate(
                    PopularMovieListFragmentDirections.actionFragmentPopularToFragmentMovieDetail(
                            it
                    )
            )
        }
}