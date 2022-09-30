package com.example.safebodatest.login.usecase

import com.example.safebodatest.core.consts.Keys
import com.example.safebodatest.core.preferences.PreferenceManager
import com.example.safebodatest.features.login.domain.usecases.StoreUserIdUC
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class LoginStoreUserIdUCTest {

    private lateinit var usecase: StoreUserIdUC

    private val userId = 1

    @Mock
    private lateinit var preferenceManager: PreferenceManager

    @Test
    fun storeToken_returnTrue() {
        preferenceManager = mock {
            on { getInt(Keys.CURRENT_USER_ID) } doReturn userId
        }
        Mockito.`when`(preferenceManager.putInt(Keys.CURRENT_USER_ID, userId)).then { }
        usecase = StoreUserIdUC(preferenceManager)
        val result = usecase.run(userId)
        result.fold(ifRight = {
            Assert.assertTrue(true)
        },
            ifLeft = {
                Assert.fail(it?.message)
            })
    }

    @Test
    fun storeToken_returnFalse() {
        val expected = -1
        preferenceManager = mock {
            on { getInt(Keys.CURRENT_USER_ID) } doReturn expected
        }
        Mockito.`when`(preferenceManager.putInt(Keys.CURRENT_USER_ID, userId)).then { }
        usecase = StoreUserIdUC(preferenceManager)
        val result = usecase.run(userId)
        result.fold(ifLeft = {
            Assert.assertTrue(true)
        },
            ifRight = {
                Assert.fail()
            })
    }

}