package com.cbedoy.feature_movielist.presentation

import androidx.lifecycle.ViewModel
import com.cbedoy.base.MVIViewModel
import com.cbedoy.base.Producer
import com.cbedoy.feature_movielist.MovieListIntent
import com.cbedoy.feature_movielist.MovieListState
import com.cbedoy.feature_movielist.domain.MoviesSortedByUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val useCase: MoviesSortedByUseCase
) : ViewModel() {

    val topRatedFlow
        get() = useCase.topRatedFlow

    val popularFlow
        get() = useCase.popularFlow

}