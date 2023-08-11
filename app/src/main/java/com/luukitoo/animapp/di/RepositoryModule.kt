package com.luukitoo.animapp.di

import com.luukitoo.anime.data.repository.AnimeRepositoryImpl
import com.luukitoo.characters.data.repository.CharactersRepositoryImpl
import com.luukitoo.anime.data.repository.ReviewsRepositoryImpl
import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.characters.domain.repository.CharactersRepository
import com.luukitoo.anime.domain.repository.ReviewsRepository
import com.luukitoo.manga.data.repository.MangaRepositoryImpl
import com.luukitoo.manga.domain.repository.MangaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAnimeRepositoryImpl(
        animeRepositoryImpl: AnimeRepositoryImpl,
    ): AnimeRepository

    @Binds
    @Singleton
    fun bindCharactersRepositoryImpl(
        charactersRepositoryImpl: CharactersRepositoryImpl,
    ): CharactersRepository

    @Binds
    @Singleton
    fun bindReviewsRepositoryImpl(
        reviewsRepositoryImpl: ReviewsRepositoryImpl,
    ): ReviewsRepository

    @Binds
    @Singleton
    fun bindMangaRepositoryImpl(
        mangaRepositoryImpl: MangaRepositoryImpl,
    ): MangaRepository
}