package com.example.registroprestamos.utils

sealed class Screen(
    val route: String
) {
    object RegistroPersonasScreen : Screen("RegistroPersonas")
    object consultaPersonasScreen : Screen("ConsultaPersonas")

    object RegistroOcupacionesScreen : Screen("RegistroOcupaciones")
    object consultaOcupacionesScreen : Screen("ConsultaOcupaciones")
}