package com.cbedoy.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.datasource.PopularMoviesPagingSource
import com.cbedoy.core.data.datasource.TopRatedMoviesPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val coroutineScope: CoroutineScope,
    private val topRatedMoviesPagingSource: TopRatedMoviesPagingSource,
    private val popularMoviesPagingSource: PopularMoviesPagingSource
) : MovieRepository {

    override val popularMoviesFlow: Flow<PagingData<Movie>>
        get() = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { popularMoviesPagingSource }
        ).flow.cachedIn(coroutineScope)

    override val topRatedMoviesFlow: Flow<PagingData<Movie>>
        get() = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { topRatedMoviesPagingSource }
        ).flow.cachedIn(coroutineScope)


}