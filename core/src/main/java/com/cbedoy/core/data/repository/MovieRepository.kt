package com.cbedoy.core.data.repository

import androidx.paging.PagingData
import com.cbedoy.core.data.database.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    val popularMoviesFlow: Flow<PagingData<Movie>>

    val topRatedMoviesFlow: Flow<PagingData<Movie>>

}