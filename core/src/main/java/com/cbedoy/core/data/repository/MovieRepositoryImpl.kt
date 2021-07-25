package com.cbedoy.core.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.datasource.PopularMovieBoundaryCallback
import com.cbedoy.core.data.datasource.TopRatedMovieBoundaryCallback
import com.cbedoy.core.data.service.MovieService
import com.cbedoy.core.data.service.MovieVideoResponse
import com.haroldadmin.cnradapter.NetworkResponse

class MovieRepositoryImpl constructor(
    private val service: MovieService,
    private val dao: MovieDao,
    private val pagedListConfig: PagedList.Config,
    private val topRatedBoundaryCallback: TopRatedMovieBoundaryCallback,
    private val popularBoundaryCallback: PopularMovieBoundaryCallback
) : MovieRepository {

    override suspend fun requestMovieVideoDetails(movieId: Long): NetworkResponse<MovieVideoResponse, Void> {
        return service.getVideoDetails(movieId = movieId)
    }

    override val popularMoviesFlow: LiveData<PagedList<Movie>>
        get() = LivePagedListBuilder(
            dao.getPopularMovies(),
            pagedListConfig
        ).setBoundaryCallback(popularBoundaryCallback).build()

    override val topRatedMoviesFlow: LiveData<PagedList<Movie>>
        get() = LivePagedListBuilder(
            dao.getPopularMovies(),
            pagedListConfig
        ).setBoundaryCallback(topRatedBoundaryCallback).build()
}