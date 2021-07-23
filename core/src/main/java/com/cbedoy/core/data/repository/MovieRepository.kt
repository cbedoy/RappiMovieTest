package com.cbedoy.core.data.repository

import com.cbedoy.core.data.database.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    val allMoviesFlow: Flow<List<Movie>>

    suspend fun loadPopularMovies(): Flow<List<Movie>>

    suspend fun loadTopRatedMovies(): Flow<List<Movie>>
}