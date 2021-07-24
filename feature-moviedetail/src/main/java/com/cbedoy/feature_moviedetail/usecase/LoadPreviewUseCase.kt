package com.cbedoy.feature_moviedetail.usecase

import com.cbedoy.core.data.repository.MovieRepository
import com.cbedoy.feature_moviedetail.presentation.MovieDetailState
import com.cbedoy.feature_moviedetail.presentation.model.PreviewVideo
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.flow

class LoadPreviewUseCase (private val repository: MovieRepository) {

    fun loadPreview(movieId: Long) = flow{
        when(val response = repository.requestMovieVideoDetails(movieId)) {
            is NetworkResponse.Success -> {
                val previews = response.body.results.filter {
                    it.site.equals("youtube", ignoreCase = true)
                }.map {
                    PreviewVideo(title = it.name, youtubeId = it.key, type = it.type)
                }
                emit(MovieDetailState.OnLoadedPreviewDetails(previews))
            }
            is NetworkResponse.UnknownError -> {
                response.error.printStackTrace()
            }
        }
        emit(MovieDetailState.Ilde)
    }
}