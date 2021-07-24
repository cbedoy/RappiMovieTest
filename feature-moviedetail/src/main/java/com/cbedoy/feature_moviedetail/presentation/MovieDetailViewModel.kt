package com.cbedoy.feature_moviedetail.presentation

import com.cbedoy.base.MVIViewModel
import com.cbedoy.base.Producer
import com.cbedoy.feature_moviedetail.usecase.LoadPreviewUseCase
import kotlinx.coroutines.CoroutineScope

class MovieDetailViewModel (
        private val useCase: LoadPreviewUseCase,
        coroutineScope: CoroutineScope
        ): MVIViewModel<MovieDetailState, MovieDetailIntent>(MovieDetailState.Ilde, coroutineScope){

    override suspend fun onCollect(intent: MovieDetailIntent, producer: Producer<MovieDetailState>) {
        when(intent) {
            is MovieDetailIntent.LoadDetails -> {
                producer(useCase.loadPreview(intent.movieId))
            }
        }
    }
}