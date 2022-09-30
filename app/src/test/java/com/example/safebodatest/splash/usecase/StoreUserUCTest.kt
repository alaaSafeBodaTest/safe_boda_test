package com.example.safebodatest.splash.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import com.example.safebodatest.features.splash_screen.domain.usecase.StoreUserUC
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock

class StoreUserUCTest {

    @Mock
    lateinit var splashRepository: ISplashRepository

    @Mock
    var user: User = mock { }

    lateinit var storeUserUC: StoreUserUC

    private val expectedRight = Either.Right(1L)

    private val expectedLeft = Either.Left(CacheFailure("Test Failure"))

    @Test
    fun storeUser_returnLong() {
        splashRepository = mockk {
            coEvery {
                saveUser(any())
            } returns expectedRight
        }
        storeUserUC = StoreUserUC(splashRepository)
        runBlocking {
            val result = storeUserUC.runAsync(user)
            Assert.assertSame(expectedRight, result)
        }
    }

    @Test
    fun storeUser_returnFailure() {
        splashRepository = mockk {
            coEvery {
                saveUser(any())
            } returns expectedLeft
        }
        storeUserUC = StoreUserUC(splashRepository)
        runBlocking {
            val result = storeUserUC.runAsync(user)
            Assert.assertSame(expectedLeft, result)
        }
    }

}