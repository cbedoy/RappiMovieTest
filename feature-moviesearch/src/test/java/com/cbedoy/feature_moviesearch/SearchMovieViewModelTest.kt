package com.cbedoy.feature_moviesearch

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchMovieViewModelTest {
    @Test
    fun `when SearchMovieWithQuery intent is invoked` () = runBlocking{
        val useCase = mockk<SearchMovieUseCase>()

        every { useCase.performSearchMovies(any()) } returns flowOf(SearchMovieState.Ilde)

        val viewModel = SearchMovieViewModel(
            useCase = useCase,
            coroutineScope = CoroutineScope(Dispatchers.IO)
        )
        viewModel.performActionWithIntent(SearchMovieIntent.SearchMovieWithQuery(query = "an query"))
        verify {
            useCase.performSearchMovies(query = "an query")
        }
    }
}