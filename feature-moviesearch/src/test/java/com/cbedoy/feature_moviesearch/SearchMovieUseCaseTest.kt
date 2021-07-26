package com.cbedoy.feature_moviesearch

import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.repository.MovieRepository
import com.cbedoy.core.data.response.MovieResponse
import com.cbedoy.core.data.response.MovieResult
import com.haroldadmin.cnradapter.NetworkResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class SearchMovieUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: MovieRepository

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when query is empty` () = runBlocking {
        val useCase = SearchMovieUseCase(
            repository = repository
        )
        val states = useCase.performSearchMovies(query = "").toList()
        val expectedStates = listOf<SearchMovieState>(
            SearchMovieState.OnShowLoader(isVisible = true),
            SearchMovieState.OnLoadResults(emptyList()),
            SearchMovieState.OnShowLoader(isVisible = false),
            SearchMovieState.Ilde
        )

        assertEquals(
            expectedStates,
            states
        )
    }

    @Test
    fun `when response fails, due network error` () = runBlocking {

        coEvery { repository.requestSearchMovie(any()) } returns NetworkResponse.NetworkError(
            IOException()
        )

        val useCase = SearchMovieUseCase(
            repository = repository
        )
        val states = useCase.performSearchMovies(query = "word").toList()
        val expectedStates = listOf(
            SearchMovieState.OnShowLoader(isVisible = true),
            SearchMovieState.OnLoadResults(emptyList()),
            SearchMovieState.OnShowLoader(isVisible = false),
            SearchMovieState.Ilde
        )

        assertEquals(
            expectedStates,
            states
        )
    }


    @Test
    fun `when response fails, due server error` () = runBlocking {

        coEvery { repository.requestSearchMovie(any()) } returns NetworkResponse.ServerError(code = 500, body = null)

        val useCase = SearchMovieUseCase(
            repository = repository
        )
        val states = useCase.performSearchMovies(query = "word").toList()
        val expectedStates = listOf(
            SearchMovieState.OnShowLoader(isVisible = true),
            SearchMovieState.OnLoadResults(emptyList()),
            SearchMovieState.OnShowLoader(isVisible = false),
            SearchMovieState.Ilde
        )

        assertEquals(
            expectedStates,
            states
        )
    }

    @Test
    fun `when results from response are success but empty` () = runBlocking {

        coEvery { repository.requestSearchMovie(any()) } returns NetworkResponse.Success(
            body = MovieResponse(),
            null,
            200
        )

        val useCase = SearchMovieUseCase(
            repository = repository
        )
        val states = useCase.performSearchMovies(query = "word").toList()
        val expectedStates = listOf(
            SearchMovieState.OnShowLoader(isVisible = true),
            SearchMovieState.OnLoadResults(emptyList()),
            SearchMovieState.OnShowLoader(isVisible = false),
            SearchMovieState.Ilde
        )

        assertEquals(
            expectedStates,
            states
        )
    }

    @Test
    fun `when results from response are success has more than one item` () = runBlocking {

        coEvery { repository.requestSearchMovie(any()) } returns NetworkResponse.Success(
            body = MovieResponse(
                results = listOf(
                    MovieResult(id = 1)
                )
            ),
            null,
            200
        )

        val useCase = SearchMovieUseCase(
            repository = repository
        )
        val states = useCase.performSearchMovies(query = "word").toList()
        val expectedStates = listOf(
            SearchMovieState.OnShowLoader(isVisible = true),
            SearchMovieState.OnLoadResults(listOf(
                Movie(uuid = 1, image = "https://image.tmdb.org/t/p/w440_and_h660_face/")
            )),
            SearchMovieState.OnShowLoader(isVisible = false),
            SearchMovieState.Ilde
        )

        assertEquals(
            expectedStates,
            states
        )
    }


}