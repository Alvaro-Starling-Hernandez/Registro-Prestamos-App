package com.example.registroprestamos.ui.ocupacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.registroprestamos.ui.componentes.RowOcupacion
import com.example.registroprestamos.utils.Screen

@Composable
fun consultaOcupacionesScreen(
    viewModel: OcupacionViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    Scaffold(

        topBar = {

            TopAppBar(
                title = { Text("Consulta De Ocupaciones") }
            )

        },
        floatingActionButton = {

            FloatingActionButton(
                onClick = { navHostController.navigate(Screen.RegistroOcupacionesScreen.route) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }

        },
        floatingActionButtonPosition = FabPosition.End

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {
            Button(onClick = { navHostController.navigate(Screen.consultaPersonasScreen.route) }) {
                Text(text = "Personas")
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ID",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Nombre",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
            }

            val listaOcupaciones = viewModel.ocupaciones.collectAsState(initial = emptyList())

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(listaOcupaciones.value) { ocupacion ->
                    RowOcupacion(ocupacion,navHostController)
                }
            }
        }
    }
}