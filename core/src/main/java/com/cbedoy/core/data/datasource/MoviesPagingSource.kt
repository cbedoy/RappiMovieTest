package com.cbedoy.core.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.response.MovieResponse
import com.cbedoy.core.data.response.MovieResult
import com.cbedoy.core.data.service.MovieService
import com.haroldadmin.cnradapter.NetworkResponse

abstract class MoviesPagingSource (
    private val service: MovieService,
    private val dao: MovieDao
) : PagingSource<Int, Movie>() {

    abstract val apiCall : suspend (Int) -> NetworkResponse<MovieResponse, Void>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key?:0

        return when(val response = apiCall(page)) {
            is NetworkResponse.Success -> {
                val results = response.body.results.map {
                    it.toMovie()
                }
                dao.insertAll(results)

                LoadResult.Page(
                    data = results,
                    prevKey = page + 1,
                    nextKey = page + 2
                )
            }
            else -> {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = page + 1,
                    nextKey = page + 2
                )
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

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}