package com.mnafis.movie_mania.di

import android.content.Context
import androidx.room.Room
import com.mnafis.movie_mania.omdb_service.OmdbManager
import com.mnafis.movie_mania.omdb_service.OmdbService
import com.mnafis.movie_mania.room_database.MovieDao
import com.mnafis.movie_mania.room_database.MovieManiaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class, SingletonComponent::class)
object MovieManiaModule {
    @Provides
    fun omdbService(): OmdbService = Retrofit.Builder()
        .baseUrl(OmdbManager.omdbBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OmdbService::class.java)

    @Provides
    fun movieDao(
        @ApplicationContext context: Context
    ): MovieDao = Room.databaseBuilder(
        context = context,
        klass = MovieManiaDatabase::class.java,
        name = MovieManiaDatabase::class.simpleName
    ).build().movieDao()

}