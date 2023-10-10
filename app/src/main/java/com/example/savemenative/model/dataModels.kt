package com.example.savemenative.model


data class User(val email:String, val password:String)

data class LoginResponse(val token:String)