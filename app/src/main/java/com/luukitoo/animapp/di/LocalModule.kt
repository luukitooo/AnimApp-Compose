package com.luukitoo.animapp.di

import android.app.Application
import com.luukitoo.database.AnimAppDatabase
import com.luukitoo.database.AnimeDataSource
import com.luukitoo.database.AnimeDataSourceImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindAnimeDataSourceImpl(
        animeDataSourceImpl: AnimeDataSourceImpl
    ) : AnimeDataSource
}