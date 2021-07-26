package com.cbedoy.feature_moviedetail.presentation

import com.cbedoy.feature_moviedetail.usecase.LoadPreviewUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieDetailViewModelTest {

    @Test
    fun `when LoadDetails intent is invoked` () = runBlocking{
        val useCase = mockk<LoadPreviewUseCase>()

        every { useCase.loadPreview(any()) } returns flowOf(MovieDetailState.Ilde)

        val viewModel = MovieDetailViewModel(
            useCase = useCase,
            coroutineScope = CoroutineScope(Dispatchers.IO)
        )
        viewModel.performActionWithIntent(MovieDetailIntent.LoadDetails(movieId = 1))
        verify {
            useCase.loadPreview(movieId = 1)
        }
    }

}