package com.cbedoy.core.data.repository

import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.response.MovieResult
import com.cbedoy.core.data.service.MovieService
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val service: MovieService,
    private val dao: MovieDao
) : MovieRepository {

    override val allMoviesFlow: Flow<List<Movie>>
        get() = dao.getAllMoviesAsFlow()

    override suspend fun loadTopRatedMovies() {
        when(val response = service.getTopRatedMovies()) {
            is NetworkResponse.Success -> {
                val movies = response.body.results.map { it.toMovie() }

                dao.insertAll(movies)
            }
            is NetworkResponse.ServerError -> {
                response.body
            }
            is NetworkResponse.UnknownError -> {
                response.error.printStackTrace()
            }
        }
    }

    override suspend fun loadPopularMovies(){
        when(val response = service.getPopularMovies()) {
            is NetworkResponse.Success -> {
                val movies = response.body.results.map { it.toMovie() }

                dao.insertAll(movies)
            }
        }
    }

    private fun MovieResult.toMovie(): Movie {
        return Movie(
            id,
            image = "https://image.tmdb.org/t/p/w440_and_h660_face/$posterPath",
            title,
            overview,
            popularity
        )
    }

}