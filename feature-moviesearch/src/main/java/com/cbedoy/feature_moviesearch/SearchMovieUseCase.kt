package com.cbedoy.feature_moviesearch

import com.cbedoy.core.data.repository.MovieRepository
import com.cbedoy.core.data.toMovie
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.flow

class SearchMovieUseCase (private val repository: MovieRepository) {

    fun performSearchMovies(query: String) = flow {
        emit(SearchMovieState.OnShowLoader(isVisible = true))

        if (query.isNotEmpty()) {
            when(val response = repository.requestSearchMovie(query)) {
                is NetworkResponse.Success -> {
                    val items = response.body.results.map { it.toMovie() }
                    emit(SearchMovieState.OnLoadResults(items))
                }
                else -> {
                    emit(SearchMovieState.OnLoadResults(emptyList()))
                }
            }
        } else {
            emit(SearchMovieState.OnLoadResults(emptyList()))
        }

        emit(SearchMovieState.OnShowLoader(isVisible = false))

        emit(SearchMovieState.Ilde)
    }

}