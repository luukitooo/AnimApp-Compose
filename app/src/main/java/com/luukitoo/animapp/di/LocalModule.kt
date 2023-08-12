package com.luukitoo.animapp.di

import android.app.Application
import com.luukitoo.database.AnimAppDatabase
import com.luukitoo.database.anime.AnimeDataSource
import com.luukitoo.database.anime.AnimeDataSourceImpl
import com.luukitoo.database.manga.MangaDataSource
import com.luukitoo.database.manga.MangaDataSourceImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import entity.AnimeEntityQueries
import entity.MangaEntityQueries
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDriver(application: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = AnimAppDatabase.Schema,
            context = application,
            name = "animapp.db"
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(driver: SqlDriver): AnimAppDatabase {
        return AnimAppDatabase.invoke(driver)
    }

    @Provides
    @Singleton
    fun provideAnimeQueries(
        database: AnimAppDatabase
    ): AnimeEntityQueries {
        return database.animeEntityQueries
    }

    @Provides
    @Singleton
    fun provideMangaQueries(
        database: AnimAppDatabase
    ): MangaEntityQueries {
        return database.mangaEntityQueries
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindAnimeDataSourceImpl(
        animeDataSourceImpl: AnimeDataSourceImpl
    ): AnimeDataSource

    @Binds
    @Singleton
    fun bindMangaDataSourceImpl(
        mangaDataSourceImpl: MangaDataSourceImpl
    ): MangaDataSource
}