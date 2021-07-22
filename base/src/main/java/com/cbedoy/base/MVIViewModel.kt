package com.cbedoy.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


typealias Producer<State> = suspend (Flow<State>) -> Unit

abstract class MVIViewModel <State, Intent>(
    initialState: State,
    coroutineScope: CoroutineScope
) {

    private val intentChannel = Channel<Intent>(Channel.UNLIMITED)

    private var _state = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = _state

    abstract suspend fun onCollect(intent: Intent, producer: Producer<State>)
    abstract val tagLogger: String

    fun performActionWithIntent(intent: Intent){
        intentChannel.offer(intent)
    }

    init {
        coroutineScope.launch {
            handleIntents()
        }
    }

    private suspend fun handleIntents(){
        intentChannel.consumeAsFlow().collect { intent ->
            println("ViewModel -> tagLogger.handleIntent($intent)")
            onCollect(intent){ flow ->
                flow.flowOn(
                    Dispatchers.IO
                ).catch { state ->
                    println("ViewModel -> $tagLogger.catch(${state.message})")
                }.collect { newState ->
                    println("ViewModel -> $tagLogger.collect($newState)")
                    _state.value = newState
                }
            }
        }
    }
}