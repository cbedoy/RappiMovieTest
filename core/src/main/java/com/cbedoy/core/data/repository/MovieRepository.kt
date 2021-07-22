package com.cbedoy.core.data.repository

import com.cbedoy.core.data.database.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun loadPopularMovies(): Flow<List<Movie>>

    suspend fun loadTopRatedMovies(): Flow<List<Movie>>
}