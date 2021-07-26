package com.cbedoy.core.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.response.MovieResponse
import com.cbedoy.core.data.service.MovieVideoResponse
import com.haroldadmin.cnradapter.NetworkResponse

interface MovieRepository {

    val popularMoviesFlow: LiveData<PagedList<Movie>>

    val topRatedMoviesFlow: LiveData<PagedList<Movie>>

    suspend fun requestMovieVideoDetails(movieId: Long): NetworkResponse<MovieVideoResponse, Void>

    suspend fun requestSearchMovie(query: String): NetworkResponse<MovieResponse, Void>

}