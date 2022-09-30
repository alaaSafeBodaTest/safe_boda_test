package com.example.safebodatest.login.datasource

import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.dao.UserDao
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.features.login.data.datasource.local_datasources.ILoginLocalDatasource
import com.example.safebodatest.features.login.data.datasource.local_datasources.LoginDBLocalDatasource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock

class LoginLocalDatasourceTest {

    private lateinit var localDatasource: ILoginLocalDatasource

    @Mock
    private lateinit var userDao: UserDao

    @Mock
    private lateinit var appDB: AppDB

    private val user: User = User(id = 1, name = "Username")

    @Test
    fun saveUser_returnsTrue() {
        val expected = listOf(user.id.toLong())
        userDao = mockk {
            coEvery { insertAll(user) } returns expected
            coEvery { getAll() } returns listOf(user)
        }
        appDB = mockk {
            coEvery { userDao() } returns userDao
        }
        localDatasource = LoginDBLocalDatasource(appDB)
        runBlocking {
            val result = localDatasource.saveUser(user)
            result.fold(ifRight = {
                Assert.assertEquals(user.id.toLong(), it)
            }, ifLeft = {
                Assert.fail(it.message)
            })
        }
    }

    @Test
    fun saveUser_returnsFailure() {
        val expected = CacheFailure("")
        userDao = mockk {
            coEvery { insertAll(user) } returns listOf()
            coEvery { getAll() } returns listOf()
        }
        appDB = mockk {
            coEvery { userDao() } returns userDao
        }
        localDatasource = LoginDBLocalDatasource(appDB)
        runBlocking {
            val result = localDatasource.saveUser(user)
            result.fold(ifLeft = {
                assert(expected.message == it.message)
            }, ifRight = {
                Assert.fail(it.toString())
            })
        }
    }
}