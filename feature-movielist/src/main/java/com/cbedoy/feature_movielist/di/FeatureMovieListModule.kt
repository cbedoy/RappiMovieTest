package com.cbedoy.feature_movielist.di

import com.cbedoy.feature_movielist.domain.MoviesSortedByUseCase
import com.cbedoy.feature_movielist.presentation.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureMovieListModule = module {
    viewModel {
        MovieListViewModel(
            useCase = get()
        )
    }
    factory {
        MoviesSortedByUseCase(
            repository = get()
        )
    }
}