package com.cbedoy.core.data.datasource

import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.service.MovieService
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class PopularMovieBoundaryCallbackTest {

    @RelaxedMockK
    private lateinit var  service: MovieService

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when onItemAtEndLoaded, get popular movies called` () {

        val boundaryCallback = PopularMovieBoundaryCallback(
            service = service
        )
        boundaryCallback.onItemAtEndLoaded(Movie())

        coVerify { service.getPopularMovies() }
    }

}