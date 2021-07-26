package com.cbedoy.rappimovie

import android.app.Application
import com.cbedoy.core.di.coreModule
import com.cbedoy.feature_moviedetail.di.featureMovieDetailModule
import com.cbedoy.feature_movielist.di.featureMovieListModule
import com.cbedoy.feature_moviesearch.di.featureMovieSearchModule
import com.cbedoy.rappimovie.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                appModule,
                coreModule,
                featureMovieListModule,
                featureMovieDetailModule,
                featureMovieSearchModule
            )
            androidContext(this@MovieApp)
        }
    }
}