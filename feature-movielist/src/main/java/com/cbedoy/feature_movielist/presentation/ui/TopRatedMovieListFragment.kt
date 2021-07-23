package com.cbedoy.feature_movielist.presentation.ui

import com.cbedoy.feature_movielist.domain.FilterMovieOption
import com.cbedoy.feature_movielist.presentation.ui.base.MovieListFragment

class TopRatedMovieListFragment : MovieListFragment(){
    override val filter: FilterMovieOption
        get() = FilterMovieOption.TopRated
}