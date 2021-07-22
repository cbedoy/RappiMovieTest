package com.cbedoy.rappimovie

import android.app.Application
import com.cbedoy.core.di.coreModule
import com.cbedoy.feature_movielist.di.featureMovieListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                coreModule,
                featureMovieListModule
            )
            androidContext(this@MovieApp)
        }
    }
}