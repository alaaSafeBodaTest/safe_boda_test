package com.example.safebodatest.splash.usecase

import arrow.core.Either
import com.example.safebodatest.core.failures.CacheFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.splash_screen.domain.repository.ISplashRepository
import com.example.safebodatest.features.splash_screen.domain.usecase.HasTokenUC
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class HasTokenUCTest {

    @Mock
    lateinit var splashRepository: ISplashRepository

    lateinit var hasTokenUC: HasTokenUC

    private val expectedRight = Either.Right("test_token")

    private val expectedLeft = Either.Left(CacheFailure("Test Failure"))

    @Test
    fun hasToken_returnTrue(){
        splashRepository = mock {
            on {
                getToken()
            } doReturn expectedRight
        }
        hasTokenUC = HasTokenUC(splashRepository)
        val result = hasTokenUC.run(IUseCaseTemplate.NoParams())
        Assert.assertSame(expectedRight, result)
    }

    @Test
    fun hasToken_returnFalse(){
        splashRepository = mock {
            on {
                getToken()
            } doReturn expectedLeft
        }
        hasTokenUC = HasTokenUC(splashRepository)
        val result = hasTokenUC.run(IUseCaseTemplate.NoParams())
        Assert.assertSame(expectedLeft, result)
    }

}