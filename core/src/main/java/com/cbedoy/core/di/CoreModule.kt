package com.cbedoy.core.di

import android.app.Application
import androidx.room.Room
import com.cbedoy.core.data.database.AppDatabase
import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.datasource.PopularMoviesPagingSource
import com.cbedoy.core.data.datasource.TopRatedMoviesPagingSource
import com.cbedoy.core.data.repository.MovieRepository
import com.cbedoy.core.data.repository.MovieRepositoryImpl
import com.cbedoy.core.data.service.MovieService
import com.cbedoy.core.feature.movie_list.MovieListViewModel
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val coreModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "movies")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePokeDao(database: AppDatabase): MovieDao {
        return database.movieDao()
    }

    fun provideRetrofit() : Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)


        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(Json{
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .client(httpClient.build())
            .build()
    }

    fun <T> provideService(retrofit: Retrofit, clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    single { provideDatabase(androidApplication()) }
    single { providePokeDao(get()) }


    single<MovieRepository>{
        MovieRepositoryImpl(
            coroutineScope = get(),
                service = get(),
            topRatedMoviesPagingSource = get(),
            popularMoviesPagingSource = get()
        )
    }

    single {
        TopRatedMoviesPagingSource(
            service = get(),
            dao = get()
        )
    }

    single {
        PopularMoviesPagingSource(
            service = get(),
            dao = get()
        )
    }

    viewModel {
        MovieListViewModel(
                repository = get()
        )
    }

    single { provideRetrofit() }
    single { provideService(get(), MovieService::class.java) }
}