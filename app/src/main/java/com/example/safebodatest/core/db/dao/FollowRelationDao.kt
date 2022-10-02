package com.example.safebodatest.core.db.dao

import androidx.room.*
import com.example.safebodatest.core.db.tables.FollowRelation

@Dao
interface FollowRelationDao {

    @Query("SELECT * FROM follow_relation")
    suspend fun getAll(): List<FollowRelation>

    @Query("SELECT * FROM follow_relation WHERE username IN (:username)")
    suspend fun loadAllByIds(username: String): List<FollowRelation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: FollowRelation): List<Long>

    @Delete
    suspend fun delete(relation: FollowRelation)

}