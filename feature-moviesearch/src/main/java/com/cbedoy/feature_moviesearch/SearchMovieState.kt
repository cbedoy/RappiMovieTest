package com.cbedoy.feature_moviesearch

import com.cbedoy.core.data.database.models.Movie

sealed class SearchMovieState  {
    data class OnShowLoader(val isVisible: Boolean): SearchMovieState()
    data class OnLoadResults(val movies: List<Movie>): SearchMovieState()
    object Ilde: SearchMovieState()
}

sealed class SearchMovieIntent {
    data class SearchMovieWithQuery(val query: String): SearchMovieIntent()
}