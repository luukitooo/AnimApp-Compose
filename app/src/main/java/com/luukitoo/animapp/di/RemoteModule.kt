package com.luukitoo.animapp.di

import com.luukitoo.anime.data.remote.service.AnimeService
import com.luukitoo.characters.data.service.CharacterService
import com.luukitoo.anime.data.remote.service.ReviewService
import com.luukitoo.core.constant.NetworkConfig
import com.luukitoo.manga.data.remote.service.MangaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimeService(retrofit: Retrofit): AnimeService {
        return retrofit.create(AnimeService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharactersService(retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewService(retrofit: Retrofit): ReviewService {
        return retrofit.create(ReviewService::class.java)
    }

    @Provides
    @Singleton
    fun provideMangaService(retrofit: Retrofit): MangaService {
        return retrofit.create(MangaService::class.java)
    }
}