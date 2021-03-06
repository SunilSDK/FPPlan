package com.rtc.feedprodution.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rtc.feedprodution.data.room.dao.UserDetailsDao
import com.rtc.feedprodution.model.userDetails.ResUserDetails

@Database(entities = [ResUserDetails::class ], version = 2,exportSchema = false)
abstract class FeedDatabase: RoomDatabase() {

    abstract fun userDetailsDao(): UserDetailsDao

    companion object{
        val DATABASE_NAME: String = "feed_production_db"
    }

}