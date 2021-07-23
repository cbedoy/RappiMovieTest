package com.cbedoy.feature_movielist.domain

import com.cbedoy.core.data.repository.MovieRepository
import com.cbedoy.feature_movielist.MovieListState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MoviesSortedByUseCase(
    private val repository: MovieRepository
) {
    fun loadMoviesFilterBy(filterMovieOption: FilterMovieOption) = flow {
        emit(MovieListState.ShowLoader(isVisible = true))

        if (filterMovieOption == FilterMovieOption.Popularity) {
            repository.loadPopularMovies()
        } else {
            repository.loadTopRatedMovies()
        }.collect {
            emit(MovieListState.OnLoadMovies(movies = it))
        }

        emit(MovieListState.ShowLoader(isVisible = false))
        emit(MovieListState.Ilde)
    }
}