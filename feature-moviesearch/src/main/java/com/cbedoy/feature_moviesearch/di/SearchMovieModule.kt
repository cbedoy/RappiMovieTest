package com.cbedoy.feature_moviesearch.di

import com.cbedoy.feature_moviesearch.SearchMovieUseCase
import com.cbedoy.feature_moviesearch.SearchMovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureMovieSearchModule = module {
    viewModel {
        SearchMovieViewModel(
            useCase = get(),
            coroutineScope = get()
        )
    }

    factory {
        SearchMovieUseCase(
            repository = get()
        )
    }
}