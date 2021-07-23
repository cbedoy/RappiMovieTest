package com.cbedoy.feature_movielist.domain

import com.cbedoy.core.data.repository.MovieRepository
import com.cbedoy.feature_movielist.MovieListState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MoviesSortedByUseCase(
    private val repository: MovieRepository
) {

    val allMoviesState = flow<MovieListState> {
        repository.allMoviesFlow.collect {
            emit(MovieListState.OnLoadMovies(it))
        }
    }

    fun loadMoviesFilterBy(filterMovieOption: FilterMovieOption) = flow {
        emit(MovieListState.ShowLoader(isVisible = true))

        if (filterMovieOption == FilterMovieOption.Popularity) {
            repository.loadPopularMovies()
        } else {
            repository.loadTopRatedMovies()
        }

        emit(MovieListState.ShowLoader(isVisible = false))
        emit(MovieListState.Ilde)
    }
}