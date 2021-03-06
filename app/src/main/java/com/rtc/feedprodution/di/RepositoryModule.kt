package com.rtc.feedprodution.di

import com.rtc.feedprodution.data.networkdata.FeedProRetrofit
import com.rtc.feedprodution.data.room.FeedDatabase
import com.rtc.feedprodution.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        feedDatabase: FeedDatabase,
        retrofit: FeedProRetrofit,
    ): MainRepository {
        return MainRepository(feedDatabase, retrofit)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(
        feedDatabase: FeedDatabase,
        retrofit: FeedProRetrofit,
    ): LoginRepository {
        return LoginRepository(feedDatabase, retrofit)
    }

    @Singleton
    @Provides
    fun provideDashboardRepository(
        feedDatabase: FeedDatabase,
        retrofit: FeedProRetrofit,
    ): DashboardRepository {
        return DashboardRepository(feedDatabase, retrofit)
    }

    @Singleton
    @Provides
    fun provideOrderDetailsRepository(
        feedDatabase: FeedDatabase,
        retrofit: FeedProRetrofit,
    ): OrderDetailsRepository {
        return OrderDetailsRepository(feedDatabase, retrofit)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        feedDatabase: FeedDatabase,
        retrofit: FeedProRetrofit,
    ): HomeRepository {
        return HomeRepository(feedDatabase, retrofit)
    }






}