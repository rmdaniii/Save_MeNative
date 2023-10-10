package com.example.savemenative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.savemenative.data.UserRepository
import com.example.savemenative.domain.LoginResult
import com.example.savemenative.domain.LoginUseCase
import com.example.savemenative.domain.LoginViewModelFactory
import com.example.savemenative.presentation.LoginViewModel
import com.example.savemenative.presentation.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userRepository = UserRepositoryImpl()
        val loginUseCase = LoginUseCase(userRepository)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(loginUseCase)).get(LoginViewModel::class.java)

        val emailEditText:EditText = findViewById(R.id.emailEditText)
        val passwordEditText:EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button= findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            lifecycleScope.launch (Dispatchers.IO){
                    loginViewModel.login(email = email, password = password)

            }

        }


        loginViewModel.loginResult.observe(this) { result ->
            when (result) {
                is LoginResult.Success -> {
                    // Handle successful login, e.g., navigate to the main screen
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                }

                is LoginResult.Error -> {
                    // Handle login error, e.g., show an error message
                    Toast.makeText(this, "Login Failed: ${result.errorMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


    }
}