package com.example.safebodatest.core.db.dao

import androidx.room.*
import com.example.safebodatest.core.db.tables.Following
import com.example.safebodatest.core.db.tables.User

@Dao
interface FollowingDao {

    @Query("SELECT * FROM following")
    suspend fun getAll(): List<Following>

    @Query("SELECT * FROM following WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<Following>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: Following): List<Long>

    @Delete
    suspend fun delete(following: Following)

}