package com.cbedoy.feature_movielist.domain

import com.cbedoy.core.data.repository.MovieRepository

class MoviesSortedByUseCase(
    repository: MovieRepository
) {
    val topRatedFlow = repository.topRatedMoviesFlow

    val popularFlow = repository.popularMoviesFlow
}