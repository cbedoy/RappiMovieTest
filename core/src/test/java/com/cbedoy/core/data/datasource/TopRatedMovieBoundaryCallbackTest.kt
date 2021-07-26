package com.cbedoy.core.data.datasource

import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.response.MovieResponse
import com.cbedoy.core.data.response.MovieResult
import com.cbedoy.core.data.service.MovieService
import com.haroldadmin.cnradapter.NetworkResponse
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test

class TopRatedMovieBoundaryCallbackTest {
    @RelaxedMockK
    private lateinit var service: MovieService
    @RelaxedMockK
    private lateinit var dao: MovieDao

    private lateinit var boundaryCallback: MovieBoundaryCallback
    @Before
    fun before() {
        MockKAnnotations.init(this)
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        boundaryCallback = TopRatedMovieBoundaryCallback(
            service = service,
            coroutineScope = spyk(coroutineScope),
            dao = dao
        )
    }

    @Test
    fun `when service return success and it has items, then dao insert them` () {
        coEvery { service.getTopRatedMovies(any(), any(), any()) } returns NetworkResponse.Success(
            MovieResponse(
                results = listOf(
                    MovieResult(id = 0, posterPath = "test")
                )
            ),
            code = 200
        )
        boundaryCallback.onItemAtEndLoaded(Movie())
        coVerify(atLeast = 1) {
            service.getTopRatedMovies()
            dao.insertAll(listOf(
                Movie(uuid = 0, image = "https://image.tmdb.org/t/p/w440_and_h660_face/test")
            ))
        }

    }

    @Test
    fun `when service return success and it has no items, then dao insert them` () {
        coEvery { service.getTopRatedMovies(any(), any(), any()) } returns NetworkResponse.Success(
            MovieResponse(
                results = emptyList()
            ),
            code = 200
        )
        boundaryCallback.onItemAtEndLoaded(Movie())
        coVerify(atLeast = 1) {
            service.getTopRatedMovies()
            dao.insertAll(emptyList())
        }

    }

    @Test
    fun `when onItemAtEndLoaded, get top rated movies called` () {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        boundaryCallback = TopRatedMovieBoundaryCallback(
            service = service,
            coroutineScope = spyk(coroutineScope),
            dao = dao
        )
        boundaryCallback.onItemAtEndLoaded(Movie())

        coVerify(atLeast = 1) { service.getTopRatedMovies() }
    }

    @Test
    fun `when onZeroItemsLoaded, get top rated movies called` () {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        boundaryCallback = TopRatedMovieBoundaryCallback(
            service = service,
            coroutineScope = spyk(coroutineScope),
            dao = dao
        )
        boundaryCallback.onZeroItemsLoaded()

        coVerify(atLeast = 1) { service.getTopRatedMovies() }
    }

    @Test
    fun `when onItemAtFrontLoaded, get top rated movies not called` () {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        boundaryCallback = TopRatedMovieBoundaryCallback(
            service = service,
            coroutineScope = spyk(coroutineScope),
            dao = dao
        )
        boundaryCallback.onItemAtFrontLoaded(Movie())

        coVerify(exactly = 0) { service.getTopRatedMovies() }
    }
}