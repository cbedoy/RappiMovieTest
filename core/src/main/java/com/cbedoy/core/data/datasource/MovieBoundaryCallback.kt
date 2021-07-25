package com.cbedoy.core.data.datasource

import androidx.paging.PagedList
import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.response.MovieResponse
import com.cbedoy.core.data.response.MovieResult
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class MovieBoundaryCallback : PagedList.BoundaryCallback<Movie>(), KoinComponent {

    private val coroutineScope by inject<CoroutineScope>()
    private val dao by inject<MovieDao>()

    private var currentPage = 1

    abstract val apiCall : suspend (Int) -> NetworkResponse<MovieResponse, Void>

    override fun onZeroItemsLoaded() {
        coroutineScope.launch { requestMovies() }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        coroutineScope.launch { requestMovies() }
    }

    private suspend fun requestMovies() {
        when(val response = apiCall(currentPage)) {
            is NetworkResponse.Success -> {
                val results = response.body.results.map {
                    it.toMovie()
                }
                dao.insertAll(results)
            }
        }
        currentPage++
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
}