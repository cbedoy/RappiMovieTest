package com.cbedoy.core.data.datasource

import com.cbedoy.core.data.response.MovieResponse
import com.cbedoy.core.data.service.MovieService
import com.haroldadmin.cnradapter.NetworkResponse

class PopularMovieBoundaryCallback(
    private val service: MovieService
) : MovieBoundaryCallback() {

    override val apiCall: suspend (Int) -> NetworkResponse<MovieResponse, Void>
        get() = {
            service.getTopRatedMovies(page = it)
        }
}