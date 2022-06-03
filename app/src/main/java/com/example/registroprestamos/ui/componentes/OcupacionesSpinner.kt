package com.example.registroprestamos.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.registroprestamos.model.Ocupacion
import com.example.registroprestamos.model.Persona
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.registroprestamos.ui.ocupacion.OcupacionViewModel
import com.example.registroprestamos.ui.persona.PersonaViewModel
import com.example.registroprestamos.utils.Screen

@Composable
fun RowOcupacion(
    ocupacion: Ocupacion,
    navHostController: NavHostController
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp)
            .clickable {
                navHostController.navigate(Screen.RegistroOcupacionesScreen.route)
            }
    ) {
        Text(ocupacion.ocupacionId.toString())
        Text(ocupacion.nombre)
    }
}

@Composable
fun RowPersona(
    persona: Persona,
    navHostController: NavHostController
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp)
            .clickable {
                navHostController.navigate(Screen.RegistroPersonasScreen.route)
            }
    ) {
        Text(persona.personaId.toString())
        Text(persona.nombres)
        Text(persona.salario.toString())
    }
}

@Composable
fun OcupacionesSpinner(
    viewModel: OcupacionViewModel = hiltViewModel(),
    personaViewModel: PersonaViewModel = hiltViewModel(),
) {

    var mExpanded by remember { mutableStateOf(false) }

    val ocupaciones = viewModel.ocupaciones.collectAsState(initial = emptyList())

    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        Modifier
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .clickable { }
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text("Ocupacion") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Work, contentDescription = null)
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            ocupaciones.value.forEach {
                DropdownMenuItem(onClick = {
                    personaViewModel.ocupacion = it.ocupacionId
                    mSelectedText = it.nombre
                    mExpanded = false
                }) {
                    Text(text = it.nombre)
                }
            }
        }
    }
}