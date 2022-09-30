package com.example.safebodatest.splash.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import com.example.safebodatest.features.splash_screen.domain.usecase.FetchUserUC
import com.example.safebodatest.features.splash_screen.domain.usecase.HasTokenUC
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchUserUCTest {

    @Mock
    lateinit var splashRepository: ISplashRepository

    @Mock
    var user: User = mock { }

    lateinit var fetchUserUC: FetchUserUC

    private val expectedRight = Either.Right(user)

    private val expectedLeft = Either.Left(CacheFailure("Test Failure"))

    @Test
    fun fetchUser_returnUser() {
        splashRepository = mockk {
            coEvery {
                getUser(false)
            } returns expectedRight
        }
        fetchUserUC = FetchUserUC(splashRepository)
        runBlocking {
            val result = fetchUserUC.runAsync(false)
            Assert.assertSame(expectedRight, result)
        }
    }

    @Test
    fun fetchUser_returnFalse() {
        splashRepository = mockk {
            coEvery {
                getUser(false)
            } returns expectedLeft
        }
        fetchUserUC = FetchUserUC(splashRepository)
        runBlocking {
            val result = fetchUserUC.runAsync(false)
            Assert.assertSame(expectedLeft, result)
        }
    }

}