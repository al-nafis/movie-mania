package com.mnafis.compose_ui_android_experiment.movie_mania.di

import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieManiaManager
import com.mnafis.compose_ui_android_experiment.movie_mania.service.OmdbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class, SingletonComponent::class)
object MovieManiaModule {
    @Provides
    fun omdbService(): OmdbService = Retrofit.Builder()
        .baseUrl(MovieManiaManager.omdbBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OmdbService::class.java)
}