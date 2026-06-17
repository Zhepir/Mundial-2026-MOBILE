package com.example.practicatpi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practicatpi.ui.screens.DetallePartidoScreen
import com.example.practicatpi.ui.screens.ListaPartidosScreen
import com.example.practicatpi.ui.screens.LoginScreen

object Routes {

    const val LOGIN = "login"
    const val LISTA_PARTIDOS = "lista_partidos"
    const val DETALLE_PARTIDO = "detalle_partido"
}

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        composable(
            route = Routes.LOGIN
        ) {

            LoginScreen(
                navController = navController
            )
        }

        composable(
            route = Routes.LISTA_PARTIDOS
        ) {

            ListaPartidosScreen(
                navController = navController
            )
        }

        composable(
            route = "${Routes.DETALLE_PARTIDO}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val id =
                backStackEntry.arguments?.getString("id")
                    ?: ""

            DetallePartidoScreen(
                id = id
            )
        }
    }
}