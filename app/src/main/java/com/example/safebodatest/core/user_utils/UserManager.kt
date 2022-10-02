package com.example.safebodatest.core.user_utils

import com.example.safebodatest.core.db.tables.User

class UserManager {

    companion object{

        private var user: User? = null

        fun getUser() = user

        fun setUser(user: User) { this.user = user}

    }

}