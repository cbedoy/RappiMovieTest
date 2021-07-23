package com.cbedoy.rappimovie.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule = module {
    factory {
        CoroutineScope(Dispatchers.IO)
    }
}