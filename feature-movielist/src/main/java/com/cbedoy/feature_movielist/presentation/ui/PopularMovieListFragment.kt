package com.cbedoy.feature_movielist.presentation.ui

import com.cbedoy.feature_movielist.domain.FilterMovieOption
import com.cbedoy.feature_movielist.presentation.ui.base.MovieListFragment

class PopularMovieListFragment : MovieListFragment() {
    override val filter: FilterMovieOption
        get() = FilterMovieOption.Popularity
}