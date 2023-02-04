package com.mnafis.compose_ui_android_experiment.movie_mania.di

import android.content.Context
import androidx.room.Room
import com.mnafis.compose_ui_android_experiment.movie_mania.local_database.MovieDao
import com.mnafis.compose_ui_android_experiment.movie_mania.local_database.MovieManiaDatabase
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieManiaManager
import com.mnafis.compose_ui_android_experiment.movie_mania.service.OmdbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class, SingletonComponent::class)
object MovieManiaModule {
    @Provides
    fun omdbService(): OmdbService = Retrofit.Builder()
        .baseUrl(MovieManiaManager.omdbBaseUrl)
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