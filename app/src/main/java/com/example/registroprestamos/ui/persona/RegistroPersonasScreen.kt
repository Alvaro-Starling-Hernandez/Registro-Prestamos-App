package com.example.registroprestamos.ui.persona

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.registroprestamos.model.Ocupacion
import com.example.registroprestamos.model.Persona
import com.example.registroprestamos.ui.componentes.OcupacionesSpinner

@Composable
fun RegistroPersonasScreen(
    navHostController: NavHostController,
    persona: Persona?,
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

            persona?.let {
                viewModel.id = persona.personaId
            }

            var nombresText by remember {
                mutableStateOf(
                    persona?.nombres ?: ""
                )
            }

            OutlinedTextField(
                value = nombresText,
                onValueChange = { nombresText = it },
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

            var emailText by remember {
                mutableStateOf(
                    persona?.email ?: ""
                )
            }

            OutlinedTextField(
                value = emailText,
                onValueChange = { emailText = it },
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

            OcupacionesSpinner(persona = persona)

            Spacer(modifier = Modifier.height(20.dp))

            var salarioText by remember {
                mutableStateOf(
                    persona?.salario?.toString() ?: ""
                )
            }

            OutlinedTextField(
                value = salarioText,
                onValueChange = { salarioText = it },
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
                    viewModel.nombres = nombresText
                    viewModel.email = emailText
                    viewModel.salario = salarioText
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