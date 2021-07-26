package com.cbedoy.feature_movielist.ui

import android.os.Build
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.feature.movie_list.MovieListViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O], manifest=Config.NONE)
class PopularMovieListFragmentTest : KoinTest{

    @RelaxedMockK
    private lateinit var viewModel: MovieListViewModel

    @RelaxedMockK
    private lateinit var observer: Observer<PagedList<Movie>>

    @Before
    fun before() {
        MockKAnnotations.init(this)

        val mockLiveData = mockk<LiveData<PagedList<Movie>>>()
        val mockPageList = mockk<PagedList<Movie>>()

        every { viewModel.topRatedFlow } returns mockLiveData
        every { viewModel.popularFlow } returns mockLiveData
        every { mockLiveData.value } returns mockPageList

        viewModel.popularFlow.observeForever(observer)
        viewModel.topRatedFlow.observeForever(observer)

        startKoin {
            listOf(
                module {
                    viewModel(override = true) { viewModel }
                }
            )
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `when PopularMovieListFragment becomes visible`() {
        launchFragmentInContainer<PopularMovieListFragment>()

        verify {
            viewModel.popularFlow
            observer.onChanged(any())
        }
        verify (exactly = 0) {
            viewModel.topRatedFlow
        }
    }
}

