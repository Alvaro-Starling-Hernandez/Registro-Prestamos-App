package com.example.registroprestamos.ui.persona

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.registroprestamos.ui.componentes.OcupacionesSpinner

@Composable
fun RegistroPersonasScreen(
    navHostController: NavHostController,
    viewModel: PersonaViewModel = hiltViewModel()
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
                title = { Text("Registro De Personas") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {

            OutlinedTextField(
                value = viewModel.nombres,
                onValueChange = { viewModel.nombres = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nombres")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Email")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OcupacionesSpinner()

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = viewModel.salario,
                onValueChange = { viewModel.salario = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Salario")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AttachMoney, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Spacer(modifier = Modifier.height(100.dp))

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