package com.cbedoy.feature_moviedetail.presentation

import com.cbedoy.feature_moviedetail.presentation.model.PreviewVideo

sealed class MovieDetailState {
    object Ilde: MovieDetailState()
    data class OnLoadedPreviewDetails(val previews: List<PreviewVideo>): MovieDetailState()
}

sealed class MovieDetailIntent {
    data class LoadDetails(val movieId: Long) : MovieDetailIntent()
}