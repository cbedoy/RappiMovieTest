package com.cbedoy.feature_moviesearch

import com.cbedoy.base.MVIViewModel
import com.cbedoy.base.Producer
import kotlinx.coroutines.CoroutineScope

class SearchMovieViewModel (private val useCase: SearchMovieUseCase, coroutineScope: CoroutineScope): MVIViewModel<SearchMovieState, SearchMovieIntent>(SearchMovieState.Ilde, coroutineScope)  {
    override suspend fun onCollect(
        intent: SearchMovieIntent,
        producer: Producer<SearchMovieState>
    ) {
        when(intent) {
            is SearchMovieIntent.SearchMovieWithQuery -> {
                producer(useCase.performSearchMovies(intent.query))
            }
        }
    }
}