package com.cbedoy.feature_moviedetail.usecase

import com.cbedoy.core.data.repository.MovieRepository
import com.cbedoy.core.data.service.MovieVideoResponse
import com.cbedoy.core.data.service.MovieVideoResponseResult
import com.cbedoy.feature_moviedetail.presentation.MovieDetailState
import com.cbedoy.feature_moviedetail.presentation.model.PreviewVideo
import com.haroldadmin.cnradapter.NetworkResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert
import junit.framework.TestCase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class LoadPreviewUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: MovieRepository

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when details are empty` () = runBlocking {
        coEvery { repository.requestMovieVideoDetails(any()) } returns NetworkResponse.Success(
            MovieVideoResponse(), code = 200
        )
        val useCase = LoadPreviewUseCase(
            repository = repository
        )
        val states = useCase.loadPreview(movieId = 1).toList()
        val expectedStates = listOf(
            MovieDetailState.OnLoadedPreviewDetails(emptyList()),
            MovieDetailState.Ilde
        )

        Assert.assertEquals(
            expectedStates,
            states
        )
    }

    @Test
    fun `when details are not coming from youtube` () = runBlocking {
        coEvery { repository.requestMovieVideoDetails(any()) } returns NetworkResponse.Success(
            MovieVideoResponse(
                results = listOf(
                    MovieVideoResponseResult(site = "netflix", name = "test", type = "video")
                )
            ), code = 200
        )
        val useCase = LoadPreviewUseCase(
            repository = repository
        )
        val states = useCase.loadPreview(movieId = 1).toList()
        val expectedStates = listOf(
            MovieDetailState.OnLoadedPreviewDetails(emptyList()),
            MovieDetailState.Ilde
        )

        Assert.assertEquals(
            expectedStates,
            states
        )
    }

    @Test
    fun `when details are coming from youtube` () = runBlocking {
        coEvery { repository.requestMovieVideoDetails(any()) } returns NetworkResponse.Success(
            MovieVideoResponse(
                results = listOf(
                    MovieVideoResponseResult(site = "youtube", name = "test", type = "video", key = "x")
                )
            ), code = 200
        )
        val useCase = LoadPreviewUseCase(
            repository = repository
        )
        val states = useCase.loadPreview(movieId = 1).toList()
        val expectedStates = listOf(
            MovieDetailState.OnLoadedPreviewDetails(
                listOf(
                    PreviewVideo(title = "test", type = "video", youtubeId = "x")
                )
            ),
            MovieDetailState.Ilde
        )

        Assert.assertEquals(
            expectedStates,
            states
        )
    }

    @Test
    fun `when details are coming from youtube, more than one` () = runBlocking {
        coEvery { repository.requestMovieVideoDetails(any()) } returns NetworkResponse.Success(
            MovieVideoResponse(
                results = listOf(
                    MovieVideoResponseResult(site = "youtube", name = "test", type = "video", key = "x"),
                    MovieVideoResponseResult(site = "youtube", name = "test_2", type = "video", key = "xx")
                )
            ), code = 200
        )
        val useCase = LoadPreviewUseCase(
            repository = repository
        )
        val states = useCase.loadPreview(movieId = 1).toList()
        val expectedStates = listOf(
            MovieDetailState.OnLoadedPreviewDetails(
                listOf(
                    PreviewVideo(title = "test", type = "video", youtubeId = "x"),
                    PreviewVideo(title = "test_2", type = "video", youtubeId = "xx")
                )
            ),
            MovieDetailState.Ilde
        )

        Assert.assertEquals(
            expectedStates,
            states
        )
    }

    @Test
    fun `when details failed due network` () = runBlocking {
        coEvery { repository.requestMovieVideoDetails(any()) } returns NetworkResponse.NetworkError(
            IOException()
        )
        val useCase = LoadPreviewUseCase(
            repository = repository
        )
        val states = useCase.loadPreview(movieId = 1).toList()
        val expectedStates = listOf(
            MovieDetailState.Ilde
        )

        Assert.assertEquals(
            expectedStates,
            states
        )
    }

    @Test
    fun `when details failed due server` () = runBlocking {
        coEvery { repository.requestMovieVideoDetails(any()) } returns NetworkResponse.ServerError(
            body = null, code = 500
        )
        val useCase = LoadPreviewUseCase(
            repository = repository
        )
        val states = useCase.loadPreview(movieId = 1).toList()
        val expectedStates = listOf(
            MovieDetailState.Ilde
        )

        Assert.assertEquals(
            expectedStates,
            states
        )
    }
}