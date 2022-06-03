package com.example.registroprestamos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registroprestamos.ui.ocupacion.RegistroOcupacionesScreen
import com.example.registroprestamos.ui.ocupacion.consultaOcupacionesScreen
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
                consultaOcupacionesScreen(navHostController = navHostController)
            }
            composable(Screen.RegistroPersonasScreen.route) {
                RegistroPersonasScreen(navHostController = navHostController)
            }
            composable(Screen.RegistroOcupacionesScreen.route) {
                RegistroOcupacionesScreen(navHostController = navHostController)
            }

        }
    }
}
















