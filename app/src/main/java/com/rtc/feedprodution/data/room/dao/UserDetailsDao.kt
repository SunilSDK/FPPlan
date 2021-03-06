package com.rtc.feedprodution.data.room.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rtc.feedprodution.model.userDetails.ResUserDetails


@Dao
interface UserDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: ResUserDetails): Long

    @Query("SELECT * FROM ResUserDetails")
    suspend fun get(): ResUserDetails

    @Query("DELETE FROM ResUserDetails")
    suspend fun clear(): Int
}