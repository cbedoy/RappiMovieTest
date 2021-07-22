package com.cbedoy.core.di

import android.app.Application
import androidx.room.Room
import com.cbedoy.core.data.database.AppDatabase
import com.cbedoy.core.data.database.dao.MovieDao
import com.cbedoy.core.data.repository.MovieRepository
import com.cbedoy.core.data.repository.MovieRepositoryImpl
import com.cbedoy.core.data.service.MovieService
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun <T> provideService(retrofit: Retrofit, clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    single { provideDatabase(androidApplication()) }
    single { providePokeDao(get()) }


    single<MovieRepository>{
        MovieRepositoryImpl(
            service = get(),
            dao = get()
        )
    }

    single { provideRetrofit() }
    single { provideService(get(), MovieService::class.java) }
}