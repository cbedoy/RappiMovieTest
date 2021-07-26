package com.cbedoy.core.feature.movie_list

import androidx.lifecycle.ViewModel
import com.cbedoy.core.data.repository.MovieRepository

class MovieListViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    val topRatedFlow
        get() = repository.topRatedMoviesFlow

    val popularFlow
        get() = repository.popularMoviesFlow

}