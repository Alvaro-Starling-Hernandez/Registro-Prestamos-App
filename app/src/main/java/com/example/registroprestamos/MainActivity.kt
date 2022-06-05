package com.example.registroprestamos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.registroprestamos.model.Ocupacion
import com.example.registroprestamos.model.Persona
import com.example.registroprestamos.ui.ocupacion.RegistroOcupacionesScreen
import com.example.registroprestamos.ui.ocupacion.ConsultaOcupacionesScreen
import com.example.registroprestamos.ui.persona.RegistroPersonasScreen
import com.example.registroprestamos.ui.persona.ConsultaPersonasScreen
import com.example.registroprestamos.ui.theme.RegistroPrestamosTheme
import com.example.registroprestamos.utils.Screen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroPrestamosTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val navHostController = rememberNavController()

        NavHost(
            navController = navHostController,
            startDestination = Screen.consultaPersonasScreen.route
        ) {

            composable(Screen.consultaPersonasScreen.route) {
                ConsultaPersonasScreen(navHostController = navHostController)
            }
            composable(Screen.consultaOcupacionesScreen.route) {
                ConsultaOcupacionesScreen(navHostController = navHostController)
            }
            composable(
                route = Screen.RegistroPersonasScreen.route + "/{entrada}",
                arguments = listOf(navArgument("entrada") { type = NavType.IntType })
            ) { backStackEntry ->
                val entrada = backStackEntry.arguments?.get("entrada")
                requireNotNull(entrada)

                when (entrada) {
                    1 -> {
                        val persona =
                            navHostController.previousBackStackEntry?.savedStateHandle?.get<Persona>(
                                "persona"
                            )
                        persona?.let {
                            Log.d("Args", it.nombres + it.personaId.toString())
                            RegistroPersonasScreen(
                                navHostController = navHostController,
                                persona = persona
                            )
                        }
                    }
                    2 -> {
                        RegistroPersonasScreen(
                            navHostController = navHostController,
                            persona = null
                        )
                    }
                }

            }
            composable(
                route = Screen.RegistroOcupacionesScreen.route + "/{entrada}",
                arguments = listOf(navArgument("entrada") { type = NavType.IntType })
            ) { backStackEntry ->
                val entrada = backStackEntry.arguments?.get("entrada")
                requireNotNull(entrada)
                when (entrada) {
                    1 -> {
                        val ocupacion =
                            navHostController.previousBackStackEntry?.savedStateHandle?.get<Ocupacion>(
                                "ocupacion"
                            )
                        ocupacion?.let {
                            Log.d("Args", it.nombre + it.ocupacionId.toString())
                            RegistroOcupacionesScreen(
                                navHostController = navHostController,
                                ocupacion = ocupacion
                            )
                        }
                    }
                    2 -> {
                        RegistroOcupacionesScreen(
                            navHostController = navHostController,
                            ocupacion = null
                        )
                    }
                }
            }

        }
    }
}
















