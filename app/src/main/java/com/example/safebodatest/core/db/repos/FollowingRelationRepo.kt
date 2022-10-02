package com.example.safebodatest.core.db.repos

import com.example.safebodatest.core.db.dao.FollowRelationDao
import com.example.safebodatest.core.db.tables.FollowRelation
import javax.inject.Inject

class FollowingRelationRepo @Inject constructor(
    private val followingRelationDao: FollowRelationDao,
) {
    suspend fun getAll(): List<FollowRelation> = followingRelationDao.getAll()

    suspend fun loadAllByIds(followingIds: String): List<FollowRelation> = followingRelationDao.loadAllByIds(followingIds)

    suspend fun insertAll(vararg followings: FollowRelation) = followingRelationDao.insertAll(*followings)

    suspend fun delete(following: FollowRelation) = followingRelationDao.delete(following)
}