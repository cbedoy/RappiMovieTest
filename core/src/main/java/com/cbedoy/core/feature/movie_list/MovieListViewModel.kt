package com.cbedoy.core.feature.movie_list

import androidx.lifecycle.ViewModel
import com.cbedoy.core.data.repository.MovieRepository

class MovieListViewModel(
    repository: MovieRepository
) : ViewModel() {

    val topRatedFlow = repository.topRatedMoviesFlow

    val popularFlow = repository.popularMoviesFlow

}