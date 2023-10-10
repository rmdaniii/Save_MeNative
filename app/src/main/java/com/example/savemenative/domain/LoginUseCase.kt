package com.example.savemenative.domain

import com.example.savemenative.model.LoginResponse
import com.example.savemenative.model.User
import com.example.savemenative.data.UserRepository

class LoginUseCase(val repository: UserRepository) {
    suspend operator fun invoke(user: User): LoginResponse {
        return repository.login(user)
    }
}