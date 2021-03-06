package com.cbedoy.core.di

import android.app.Application
import androidx.paging.PagedList
import androidx.room.Room
import com.cbedoy.core.data.database.AppDatabase
import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.datasource.PopularMovieBoundaryCallback
import com.cbedoy.core.data.datasource.TopRatedMovieBoundaryCallback
import com.cbedoy.core.data.repository.MovieRepository
import com.cbedoy.core.data.repository.MovieRepositoryImpl
import com.cbedoy.core.data.service.MovieService
import com.cbedoy.core.feature.movie_list.MovieListViewModel
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
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

    fun providePagedListConfig() = PagedList.Config.Builder()
        .setPrefetchDistance(50)
        .setPageSize(20).build()

    single { providePagedListConfig() }
    single { provideDatabase(androidApplication()) }
    single { providePokeDao(get()) }

    factory {
        CoroutineScope(Dispatchers.IO)
    }


    single<MovieRepository>{
        MovieRepositoryImpl(
            service = get(),
            dao = get(),
            pagedListConfig = get(),
            topRatedBoundaryCallback = get(),
            popularBoundaryCallback = get()
        )
    }

    single {
        TopRatedMovieBoundaryCallback(
            service = get(),
            coroutineScope = get(),
            dao = get()
        )
    }

    single {
        PopularMovieBoundaryCallback(
            service = get(),
            coroutineScope = get(),
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