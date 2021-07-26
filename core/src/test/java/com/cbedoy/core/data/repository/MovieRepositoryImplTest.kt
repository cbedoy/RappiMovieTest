package com.cbedoy.core.data.repository

import androidx.paging.PagedList
import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.datasource.PopularMovieBoundaryCallback
import com.cbedoy.core.data.datasource.TopRatedMovieBoundaryCallback
import com.cbedoy.core.data.service.MovieService
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieRepositoryImplTest  {

    @RelaxedMockK
    private lateinit var service: MovieService

    @RelaxedMockK
    private lateinit var dao: MovieDao

    @RelaxedMockK
    private lateinit var pagedListConfig: PagedList.Config

    @RelaxedMockK
    private lateinit var topRatedBoundaryCallback: TopRatedMovieBoundaryCallback

    @RelaxedMockK
    private lateinit var popularBoundaryCallback: PopularMovieBoundaryCallback

    @Test
    fun `when request search movie, service invoke getSearchMovie` () {
        MockKAnnotations.init(this)
        val repository = MovieRepositoryImpl(
            service = service,
            dao = dao,
            pagedListConfig = pagedListConfig,
            topRatedBoundaryCallback = topRatedBoundaryCallback,
            popularBoundaryCallback = popularBoundaryCallback
        )
        runBlocking {
            repository.requestSearchMovie(query = "poke")
        }
        coVerify {
            service.getSearchMovie(query = "poke")
        }
    }

    @Test
    fun `when request movie video details, service invoke getVideoDetails` () {
        MockKAnnotations.init(this)
        val repository = MovieRepositoryImpl(
            service = service,
            dao = dao,
            pagedListConfig = pagedListConfig,
            topRatedBoundaryCallback = topRatedBoundaryCallback,
            popularBoundaryCallback = popularBoundaryCallback
        )
        runBlocking {
            repository.requestMovieVideoDetails(movieId = 1234)
        }
        coVerify {
            service.getVideoDetails(movieId = 1234)
        }
    }


}