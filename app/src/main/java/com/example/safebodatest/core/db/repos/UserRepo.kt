package com.example.safebodatest.core.db.repos

import com.example.safebodatest.core.db.dao.UserDao
import com.example.safebodatest.core.db.tables.User
import javax.inject.Inject

class UserRepo @Inject constructor(
    private val userDao: UserDao,
) {
    suspend fun getAll(): List<User> = userDao.getAll()

    suspend fun loadAllByIds(usernames: IntArray): List<User> = userDao.loadAllByIds(usernames)

    suspend fun insertAll(vararg users: User) = userDao.insertAll(*users)

    suspend fun delete(user: User) = userDao.delete(user)
}