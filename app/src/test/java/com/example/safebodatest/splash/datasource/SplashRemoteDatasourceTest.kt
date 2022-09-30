package com.example.safebodatest.splash.datasource

import com.example.safebodatest.core.api.APIs
import com.example.safebodatest.core.api.ServiceGenerator
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource.ISplashRemoteDatasource
import com.example.safebodatest.features.splash_screen.data.datasource.remote_datasource.SplashRemoteDatasourceImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import retrofit2.Response

class SplashRemoteDatasourceTest {


    private var user: User = User(id = 1, name = "Testing user")

    @Mock
    lateinit var mockedApi: APIs

    private val remoteDatasource: ISplashRemoteDatasource = SplashRemoteDatasourceImpl()


    @Test
    fun getUser_returnUser() {
        mockedApi = mockk {
            coEvery { getUser() } returns Response.success(200, user)
        }
        ServiceGenerator.api = mockedApi
        runBlocking {
            val result = remoteDatasource.getUser()
            result.fold(ifLeft = {
                Assert.fail(it.message)
            }, ifRight = {
                Assert.assertTrue(true)
            })
        }
    }

    @Test
    fun getUser_returnHttpError() {
        mockedApi = mockk {
            coEvery { getUser() } returns Response.error(404, "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
        ServiceGenerator.api = mockedApi
        runBlocking {
            val result = remoteDatasource.getUser()
            result.fold(ifLeft = {
                Assert.assertTrue(it.message, true)
            }, ifRight = {
                Assert.fail("Has USer")
            })
        }
    }

}