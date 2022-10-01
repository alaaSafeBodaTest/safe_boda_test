package com.example.safebodatest.core.db.repos

import com.example.safebodatest.core.db.dao.FollowingDao
import com.example.safebodatest.core.db.tables.Following
import javax.inject.Inject

class FollowingRepo @Inject constructor(
    private val followingDao: FollowingDao,
) {
    suspend fun getAll(): List<Following> = followingDao.getAll()

    suspend fun loadAllByIds(followingIds: IntArray): List<Following> = followingDao.loadAllByIds(followingIds)

    suspend fun insertAll(vararg followings: Following) = followingDao.insertAll(*followings)

    suspend fun delete(following: Following) = followingDao.delete(following)
}