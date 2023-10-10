package com.example.savemenative.domain

import com.example.savemenative.model.LoginResponse

open class LoginResult {

    data class Success(val response: LoginResponse) : LoginResult()
    data class Error(val errorMessage: String) : LoginResult()

}