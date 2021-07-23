package com.cbedoy.feature_movielist.presentation

import com.cbedoy.base.MVIViewModel
import com.cbedoy.base.Producer
import com.cbedoy.feature_movielist.MovieListIntent
import com.cbedoy.feature_movielist.MovieListState
import com.cbedoy.feature_movielist.domain.MoviesSortedByUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListViewModel(
    coroutineScope: CoroutineScope,
    private val useCase: MoviesSortedByUseCase
) : MVIViewModel<MovieListState, MovieListIntent>(MovieListState.Ilde, coroutineScope) {

    override suspend fun onCollect(intent: MovieListIntent, producer: Producer<MovieListState>) {
        when(intent) {
            is MovieListIntent.LoadMoviesSortedBy -> {
                producer(useCase.loadMoviesFilterBy(intent.filterMovieOption))
            }
        }
    }

}