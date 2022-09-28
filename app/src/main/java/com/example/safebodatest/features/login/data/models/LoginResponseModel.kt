package com.example.safebodatest.features.login.data.models

import com.example.safebodatest.features.login.domain.entities.LoginResponseEntity

class LoginResponseModel(username: String, token: String, email:String): LoginResponseEntity(username, email, token)