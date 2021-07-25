package com.cbedoy.feature_movielist.ui

import androidx.navigation.fragment.findNavController
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.datasource.FilterMovieOption
import com.cbedoy.core.feature.movie_list.MovieListFragment

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