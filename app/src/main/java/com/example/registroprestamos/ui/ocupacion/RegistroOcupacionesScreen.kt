package com.example.registroprestamos.ui.ocupacion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun RegistroOcupacionesScreen(
    viewModel: OcupacionViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBAck",
                        modifier = Modifier.clickable {
                            navHostController.popBackStack()
                        }
                    )
                },
                title = { Text("Registro De Ocupaciones") }
            )
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)) {

            OutlinedTextField(
                value = viewModel.nombre,
                onValueChange = { viewModel.nombre = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nombre")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Work, contentDescription = null)
                }
            )

            Spacer(modifier = Modifier.height(200.dp))

            Button(
                onClick = {
                    viewModel.Guardar()
                    navHostController.navigateUp()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Guarduar")
            }
        }
    }
}