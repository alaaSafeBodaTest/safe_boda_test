package com.example.safebodatest.login.repository

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.failures.RemoteFailure
import com.example.safebodatest.features.login.data.datasource.local_datasources.ILoginLocalDatasource
import com.example.safebodatest.features.login.data.datasource.remote_datasources.ILoginRemoteDatasource
import com.example.safebodatest.features.login.data.repositories.LoginRepoImpl
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock

class LoginRepositoryTest {

    @Mock
    private lateinit var localDatasource: ILoginLocalDatasource

    @Mock
    private lateinit var remoteDatasource: ILoginRemoteDatasource

    private lateinit var repository: ILoginRepository

    private val user: User = User(id = 1, name = "Username")

    @Test
    fun fetchUser_returnUser() {
        val expected = Either.Right(user)
        localDatasource = mock { }
        remoteDatasource = mockk {
            coEvery { fetchUser() } returns expected
        }
        repository = LoginRepoImpl(localDatasource, remoteDatasource)
        runBlocking {
            val result = repository.fetchUser()
            result.fold(ifRight = {
                Assert.assertEquals(user.id, it.id)
            }, ifLeft = {
                Assert.fail(it.message)
            })
        }
    }

    @Test
    fun fetchUser_returnFailure() {
        val expected = Either.Left(RemoteFailure("Not Found", 404))
        localDatasource = mock { }
        remoteDatasource = mockk {
            coEvery { fetchUser() } returns expected
        }
        repository = LoginRepoImpl(localDatasource, remoteDatasource)
        runBlocking {
            val result = repository.fetchUser()
            result.fold(ifLeft = {
                it as RemoteFailure
                Assert.assertEquals(expected.value.code, it.code)
            }, ifRight = {
                Assert.fail(it.id.toString())
            })
        }
    }

    @Test
    fun storeUser_returnLong() {
        val expected = Either.Right(user.id.toLong())
        localDatasource = mockk {
            coEvery {
                saveUser(user)
            } returns expected
        }
        remoteDatasource = mockk {}
        repository = LoginRepoImpl(localDatasource, remoteDatasource)
        runBlocking {
            val result = repository.storeUserDetails(user)
            Assert.assertSame(expected, result)
        }
    }

    @Test
    fun storeUser_returnFailure() {
        val expected = Either.Left(CacheFailure("Failed"))
        localDatasource = mockk {
            coEvery {
                saveUser(user)
            } returns expected
        }
        remoteDatasource = mockk {}
        repository = LoginRepoImpl(localDatasource, remoteDatasource)
        runBlocking {
            val result = repository.storeUserDetails(user)
            Assert.assertSame(expected, result)
        }
    }

}