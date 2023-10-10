package com.example.savemenative.presentation

import com.example.savemenative.data.ApiClent
import com.example.savemenative.data.UserRepository
import com.example.savemenative.data.UserService
import com.example.savemenative.model.LoginResponse
import com.example.savemenative.model.User
import java.io.IOException

class UserRepositoryImpl  : UserRepository {

    private val userService: UserService = ApiClent.retrofit.create(UserService::class.java)

    override suspend fun login(user: User): LoginResponse {
        try {
            val response = userService.login(user.email, user.password)

            if (response.isSuccessful) {
                return response.body() ?: throw IOException("Empty response body")
            } else {
                throw IOException("Login failed: ${response.message()}")
            }
        } catch (e: Exception) {
            throw IOException("Login failed: ${e.message}", e)
        }
    }
}