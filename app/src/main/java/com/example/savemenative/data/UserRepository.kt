package com.example.savemenative.data

import com.example.savemenative.model.LoginResponse
import com.example.savemenative.model.User

interface UserRepository {
    suspend fun login(user: User): LoginResponse
}