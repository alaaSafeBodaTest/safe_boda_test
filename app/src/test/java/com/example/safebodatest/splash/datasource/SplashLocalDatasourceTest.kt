package com.example.safebodatest.splash.datasource

import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.db.AppDB
import com.example.safebodatest.core.db.dao.UserDao
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.features.splash_screen.data.datasource.local_datasource.ISplashLocalDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.local_datasource.SplashLocalDatasource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock

class SplashLocalDatasourceTest {

    @Mock
    lateinit var preferenceManager: PreferenceManager

    @Mock
    lateinit var appDB: AppDB

    @Mock
    lateinit var userDao: UserDao

    @Mock
    var user: User = mock{}

    private lateinit var localDatasource: ISplashLocalDatasource

    @Test
    fun getToken_returnString() {
        val expected = "test_token"
        preferenceManager = mockk {
            coEvery {
                getString(Keys.TOKEN)
            } returns expected
        }
        appDB = mockk {}
        localDatasource = SplashLocalDatasource(manager = preferenceManager, db = appDB)
        val result = localDatasource.getToken()
        result.fold({
            Assert.fail(it.message)
        }, {
            Assert.assertSame(it, expected)
        })
    }

    @Test
    fun getToken_returnEmptyString() {
        val expected = ""
        preferenceManager = mockk {
            coEvery {
                getString(Keys.TOKEN)
            } returns expected
        }
        appDB = mockk {}
        localDatasource = SplashLocalDatasource(manager = preferenceManager, db = appDB)
        val result = localDatasource.getToken()
        result.fold({
            Assert.assertNotEquals(it.message, "")
        }, {
            Assert.fail(it)
        })
    }

    @Test
    fun saveUser_returnId() {
        val expected = listOf(1L)
        preferenceManager = mock { }
        userDao = mockk {
            coEvery {
                insertAll(user)
            } returns expected
        }
        appDB = mockk {
            coEvery { userDao() } returns userDao
        }
        localDatasource = SplashLocalDatasource(manager = preferenceManager, appDB)
        runBlocking {
            val result = localDatasource.saveUser(user)
            result.fold( ifLeft = {
                Assert.fail(it.message)
            }, ifRight = {
                assert(it > 0)
            })
        }
    }

    @Test
    fun saveUser_returnFailure() {
        val expected = listOf<Long>()
        preferenceManager = mock { }
        userDao = mockk {
            coEvery {
                insertAll(user)
            } returns expected
        }
        appDB = mockk {
            coEvery { userDao() } returns userDao
        }
        localDatasource = SplashLocalDatasource(preferenceManager, appDB)
        runBlocking {
            val result = localDatasource.saveUser(user)
            result.fold( ifLeft = {
                Assert.assertTrue(it.message, true)
            }, ifRight = {
                Assert.fail()
            })
        }
    }

    @Test
    fun getUser_returnUser() {
        val userId = 1L
        val expected = listOf(user)
        preferenceManager = mockk {
            every { getInt(Keys.CURRENT_USER_ID) } returns userId.toInt()
        }
        userDao = mockk {
            coEvery {
                loadAllByIds(intArrayOf(userId.toInt()))
            } returns expected
        }
        appDB = mockk {
            coEvery { userDao() } returns userDao
        }
        localDatasource = SplashLocalDatasource(preferenceManager, appDB)
        runBlocking {
            val result = localDatasource.getUser()
            result.fold( ifLeft = {
                Assert.fail(it.message)
            }, ifRight = {
                Assert.assertTrue(true)
            })
        }
    }

}