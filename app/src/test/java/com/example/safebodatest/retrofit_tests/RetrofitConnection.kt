package com.example.safebodatest.retrofit_tests

import com.example.safebodatest.core.api.ServiceGenerator
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RetrofitConnection {

    @Test
    fun connection_isSuccessful(){
        runBlocking {
            val result = ServiceGenerator.api.testApi()
            assert(result.isSuccessful)
        }
    }

}