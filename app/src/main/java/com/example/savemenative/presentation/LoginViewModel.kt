package com.example.savemenative.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savemenative.domain.LoginResult
import com.example.savemenative.domain.LoginUseCase
import com.example.savemenative.model.User

class LoginViewModel (val loginUseCase: LoginUseCase): ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

     suspend fun login(email:String, password:String){
        val  user = User(email, password)
        try {
            val  response = loginUseCase(user)
            _loginResult.value = LoginResult.Success(response)
        } catch (e: Exception){
            _loginResult.value = LoginResult.Error(e.message ?: "Login failed Error")
        }

    }
}