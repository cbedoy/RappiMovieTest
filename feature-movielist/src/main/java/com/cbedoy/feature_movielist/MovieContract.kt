package com.cbedoy.feature_movielist

import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.datasource.FilterMovieOption

sealed class MovieListState {
    object Ilde: MovieListState()
    data class ShowLoader(val isVisible: Boolean): MovieListState()
    data class OnLoadMovies(val movies: List<Movie>): MovieListState()
}

sealed class MovieListIntent {
    data class LoadMoviesSortedBy(val filterMovieOption: FilterMovieOption = FilterMovieOption.All): MovieListIntent()
}