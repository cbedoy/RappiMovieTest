package com.cbedoy.core.data.repository

import androidx.paging.PagingData
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.service.MovieVideoResponse
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapter
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    val popularMoviesFlow: Flow<PagingData<Movie>>

    val topRatedMoviesFlow: Flow<PagingData<Movie>>

    suspend fun requestMovieVideoDetails(movieId: String): NetworkResponse<MovieVideoResponse, Void>

}