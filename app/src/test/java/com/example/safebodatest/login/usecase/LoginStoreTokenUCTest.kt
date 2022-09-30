package com.example.safebodatest.login.usecase

import android.content.Context
import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.features.login.data.repositories.LoginRepoImpl
import com.example.safebodatest.features.login.domain.repositories.ILoginRepository
import com.example.safebodatest.features.login.domain.usecases.StoreTokenUC
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.security.Key

class LoginStoreTokenUCTest {

    private lateinit var usecase: StoreTokenUC

    private val token = "test_Token"

    @Mock
    private lateinit var preferenceManager: PreferenceManager

    @Test
    fun storeToken_returnTrue(){
        preferenceManager = mock {
            on { getString(Keys.TOKEN) } doReturn token
        }
        Mockito.`when`(preferenceManager.putString(Keys.TOKEN, token)).then {  }
        usecase = StoreTokenUC(preferenceManager)
        val result = usecase.run(token)
        result.fold(ifRight = {
            Assert.assertTrue(true)
        },
            ifLeft = {
                Assert.fail(it?.message)
            })
    }

    @Test
    fun storeToken_returnFalse(){
        preferenceManager = mock {
            on { getString(Keys.TOKEN) } doReturn ""
        }
        Mockito.`when`(preferenceManager.putString(Keys.TOKEN, token)).then {  }
        usecase = StoreTokenUC(preferenceManager)
        val result = usecase.run(token)
        result.fold(ifRight = {
            Assert.fail()
        },
            ifLeft = {
                Assert.assertTrue(it?.message, true)
            })
    }

}