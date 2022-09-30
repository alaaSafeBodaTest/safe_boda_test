package com.example.safebodatest.login.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import com.example.safebodatest.features.login.domain.usecases.StoreUserDetailsUC
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock

class LoginStoreUserDetailsUCTest {

    private lateinit var usecase: StoreUserDetailsUC

    @Mock
    private lateinit var repository: ILoginRepository

    @Mock
    private var user: User = mock {  }

    @Test
    fun storeUserDetails_success() {
        val expected = Either.Right(1L)
        repository = mockk {
            coEvery { storeUserDetails(user) } returns expected
        }
        usecase = StoreUserDetailsUC(repository)
        runBlocking {
            val result = usecase.runAsync(user)
            result.fold(ifLeft = {
                Assert.fail(it?.message)
            }, ifRight = {
                Assert.assertEquals(1L, it)
            })
        }
    }

    @Test
    fun storeUserDetails_failed() {
        val expected = Either.Left(CacheFailure("Not Stored"))
        repository = mockk {
            coEvery { storeUserDetails(user) } returns expected
        }
        usecase = StoreUserDetailsUC(repository)
        runBlocking {
            val result = usecase.runAsync(user)
            result.fold(ifRight = {
                Assert.fail(it.toString())
            }, ifLeft = {
                Assert.assertTrue(it?.message, true)
            })
        }
    }

}