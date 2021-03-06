package com.rtc.feedprodution.di

import android.content.Context
import androidx.room.Room
import com.rtc.feedprodution.data.room.FeedDatabase
import com.rtc.feedprodution.data.room.dao.UserDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideFeedDb(@ApplicationContext context: Context): FeedDatabase {
        return Room
            .databaseBuilder(
                context,
                FeedDatabase::class.java,
                FeedDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideUserDetailsDao(feedDatabase: FeedDatabase): UserDetailsDao {
        return feedDatabase.userDetailsDao()
    }
}