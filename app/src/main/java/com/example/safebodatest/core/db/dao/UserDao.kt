package com.example.safebodatest.core.db.dao

import androidx.room.*
import com.example.safebodatest.core.db.tables.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: User): List<Long>

    @Delete
    suspend fun delete(user: User)

}