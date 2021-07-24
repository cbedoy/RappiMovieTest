package com.cbedoy.feature_moviedetail.di

import com.cbedoy.feature_moviedetail.presentation.MovieDetailViewModel
import com.cbedoy.feature_moviedetail.usecase.LoadPreviewUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureMovieDetailModule = module {
    viewModel {
        MovieDetailViewModel(
                useCase = get(),
                coroutineScope = get()
        )
    }

    factory {
        LoadPreviewUseCase(
                repository = get()
        )
    }
}