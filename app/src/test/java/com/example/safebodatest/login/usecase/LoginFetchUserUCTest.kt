package com.example.safebodatest.login.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import com.example.safebodatest.features.login.domain.usecases.FetchUserUC
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock

class LoginFetchUserUCTest {

    @Mock
    lateinit var repository: ILoginRepository

    @Mock
    var user: User = mock { }

    lateinit var fetchUserUC: FetchUserUC

    private val expectedRight = Either.Right(user)

    private val expectedLeft = Either.Left(CacheFailure("Test Failure"))

    @Test
    fun fetchUser_returnUser() {
        repository = mockk {
            coEvery {
                fetchUser()
            } returns expectedRight
        }
        fetchUserUC = FetchUserUC(repository)
        runBlocking {
            val result = fetchUserUC.runAsync(IUseCaseTemplate.NoParams())
            Assert.assertSame(expectedRight, result)
        }
    }

    @Test
    fun fetchUser_returnFalse() {
        repository = mockk {
            coEvery {
                fetchUser()
            } returns expectedLeft
        }
        fetchUserUC = FetchUserUC(repository)
        runBlocking {
            val result = fetchUserUC.runAsync(IUseCaseTemplate.NoParams())
            Assert.assertSame(expectedLeft, result)
        }
    }

}