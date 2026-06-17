package com.example.practicatpi.viewmodels

import androidx.lifecycle.ViewModel
import com.example.practicatpi.ui.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow<LoginUiState>(
            LoginUiState.Idle
        )

    val uiState: StateFlow<LoginUiState>
        get() = _uiState

    fun login(
        email: String,
        password: String
    ) {

        _uiState.value = LoginUiState.Loading

        if (
            email == "maurozinixd@gmail.com"
            &&
            password == "1234"
        ) {

            _uiState.value =
                LoginUiState.Success

        } else {

            _uiState.value =
                LoginUiState.Error(
                    "Usuario o contraseña incorrectos"
                )
        }
    }

    fun resetState() {

        _uiState.value =
            LoginUiState.Idle
    }
}