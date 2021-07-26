package com.cbedoy.core.data.datasource

import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.service.MovieService
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class PopularMovieBoundaryCallbackTest : KoinTest{
    @RelaxedMockK
    private lateinit var  service: MovieService

    @Before
    fun before() {
        MockKAnnotations.init(this)

        val coroutineScope = mockk<CoroutineScope>()
        val dao = mockk<MovieDao>()

        startKoin {
            listOf(module {
                single { dao }
                factory { coroutineScope }
                single { service }
            })
        }
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