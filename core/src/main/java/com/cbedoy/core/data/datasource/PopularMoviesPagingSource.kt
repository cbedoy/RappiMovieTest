package com.cbedoy.core.data.datasource

import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.response.MovieResponse
import com.cbedoy.core.data.service.MovieService
import com.haroldadmin.cnradapter.NetworkResponse

class PopularMoviesPagingSource(
    private val service: MovieService,
    dao: MovieDao
) : MoviesPagingSource(service, dao) {

    override val apiCall: suspend (Int) -> NetworkResponse<MovieResponse, Void>
        get() = {
            service.getTopRatedMovies(page = it)
        }
}