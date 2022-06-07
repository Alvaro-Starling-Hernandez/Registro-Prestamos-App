@file:Suppress("IMPLICIT_CAST_TO_ANY", "ControlFlowWithEmptyBody")

package com.example.registroprestamos.ui.ocupacion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.registroprestamos.model.Ocupacion
import com.example.registroprestamos.model.Persona

@Composable
fun RegistroOcupacionesScreen(
    viewModel: OcupacionViewModel = hiltViewModel(),
    navHostController: NavHostController,
    ocupacion: Ocupacion?
) {

    var nameError by rememberSaveable { mutableStateOf(false) }

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
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {

            var nombreText by rememberSaveable {
                mutableStateOf(
                    ocupacion?.nombre ?: ""
                )
            }

            ocupacion?.let {
                viewModel.id = ocupacion.ocupacionId
            }

            OutlinedTextField(
                value = nombreText,
                onValueChange = {
                    nombreText = it
                    nameError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nombre")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Work, contentDescription = null)
                },
                isError = nameError
            )

            val assistiveElementText = if(nameError) "Error: Obligatrio" else "*Obligatorio"
            val assitiveElementColor = if(nameError){
                MaterialTheme.colors.error
            }else{
                MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
            }

            Text(
                text = assistiveElementText,
                color = assitiveElementColor,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(200.dp))

            Button(
                onClick = {
                    nameError = nombreText.isBlank()
                    if(!nameError){
                        viewModel.nombre = nombreText
                        viewModel.Guardar()
                        navHostController.navigateUp()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Guarduar")
            }
        }
    }
}