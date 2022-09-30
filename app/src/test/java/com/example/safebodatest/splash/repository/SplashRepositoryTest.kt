package com.example.safebodatest.splash.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.features.splash_screen.data.datasource.local_datasource.ISplashLocalDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource.ISplashRemoteDatasource
import com.example.safebodatest.features.splash_screen.data.repository.SplashRepositoryImpl
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock

class SplashRepositoryTest {

    @Mock
    lateinit var localDatasource: ISplashLocalDatasource

    @Mock
    lateinit var remoteDatasource: ISplashRemoteDatasource

    @Mock
    var user: User = mock {  }

    lateinit var repository: ISplashRepository

    @Test
    fun getToken_returnFailure(){
        val expected = Either.Left(CacheFailure("Not Found"))
        localDatasource = mockk {
            coEvery {
                getToken()
            } returns expected
        }
        remoteDatasource = mockk {}
        repository = SplashRepositoryImpl(localDatasource,remoteDatasource)
        val result = repository.getToken()
        Assert.assertSame(expected, result)
    }

    @Test
    fun getToken_returnString(){
        val expected = Either.Right("test_token")
        localDatasource = mockk {
            coEvery {
                getToken()
            } returns expected
        }
        remoteDatasource = mockk {}
        repository = SplashRepositoryImpl(localDatasource,remoteDatasource)
        val result = repository.getToken()
        Assert.assertSame(expected, result)
    }

    @Test
    fun fetchUserLocally_returnUser(){
        val expected = Either.Right(user)
        localDatasource = mockk {
            coEvery {
                getUser()
            } returns expected
        }
        remoteDatasource = mockk {}
        repository = SplashRepositoryImpl(localDatasource,remoteDatasource)
        runBlocking {
            val result = repository.getUser(false)
            Assert.assertSame(expected, result)
        }
    }

    @Test
    fun fetchUserRemotely_returnUser(){
        val expected = Either.Right(user)
        remoteDatasource = mockk {
            coEvery {
                getUser()
            } returns expected
        }
        localDatasource = mockk {}
        repository = SplashRepositoryImpl(localDatasource,remoteDatasource)
        runBlocking {
            val result = repository.getUser(true)
            Assert.assertSame(expected, result)
        }
    }

    @Test
    fun fetchUser_returnFailure(){
        val expected = Either.Left(CacheFailure("Test Failure"))
        remoteDatasource = mockk {
            coEvery {
                getUser()
            } returns expected
        }
        localDatasource = mockk {}
        repository = SplashRepositoryImpl(localDatasource,remoteDatasource)
        runBlocking {
            val result = repository.getUser(true)
            Assert.assertSame(expected, result)
        }
    }

    @Test
    fun storeUser_returnLong(){
        val expected = Either.Right(1L)
        localDatasource = mockk {
            coEvery {
                saveUser(user)
            } returns expected
        }
        remoteDatasource = mockk {}
        repository = SplashRepositoryImpl(localDatasource,remoteDatasource)
        runBlocking {
            val result = repository.saveUser(user)
            Assert.assertSame(expected, result)
        }
    }


}