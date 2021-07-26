package com.cbedoy.core.feature.movie_list

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.data.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import org.junit.Test

class MovieListViewModelTest {

    @RelaxedMockK
    private lateinit var repository: MovieRepository

    @RelaxedMockK
    private lateinit var liveData: LiveData<PagedList<Movie>>

    @Test
    fun `view should should return expected live data objects` () {
        MockKAnnotations.init(this)
        every { repository.popularMoviesFlow } returns liveData
        every { repository.topRatedMoviesFlow } returns liveData
        val viewModel = MovieListViewModel(
            repository = repository
        )
        assertEquals(liveData, viewModel.popularFlow)
        assertEquals(liveData, viewModel.topRatedFlow)
    }
}