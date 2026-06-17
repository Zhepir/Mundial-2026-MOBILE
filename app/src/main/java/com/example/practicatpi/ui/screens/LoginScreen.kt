package com.example.practicatpi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.practicatpi.ui.navigation.Routes
import com.example.practicatpi.ui.state.LoginUiState
import com.example.practicatpi.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {

        if (uiState is LoginUiState.Success) {

            navController.navigate(
                Routes.LISTA_PARTIDOS
            ) {

                popUpTo(Routes.LOGIN) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Mundial 2026",
            color = Color.Blue,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Contraseña")
            },
            visualTransformation =
            PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                viewModel.login(
                    email,
                    password
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Ingresar"
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        when (uiState) {

            is LoginUiState.Loading -> {

                CircularProgressIndicator()
            }

            is LoginUiState.Error -> {

                Text(
                    text = (uiState as LoginUiState.Error).mensaje,
                    color = Color.Red
                )
            }

            else -> {}
        }
    }
}