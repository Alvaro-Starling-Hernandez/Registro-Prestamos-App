package com.example.registroprestamos

import android.os.Bundle
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registroprestamos.ui.theme.RegistroPrestamosTheme

var selectedOcupacion: String? = null

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

        NavHost(navController = navHostController, startDestination = "ConsultaPersonas"){

            composable("ConsultaPersonas"){
                consultaPersonasScreen(navHostController = navHostController)
            }
            composable("ConsultaOcupaciones"){
                consultaOcupacionesScreen(navHostController = navHostController)
            }
            composable("RegistroPersonas"){
                RegistroPersonasScreen(navHostController = navHostController)
            }
            composable("RegistroOcupaciones"){
                RegistroOcupacionesScreen(navHostController = navHostController)
            }

        }
    }
}

@Composable
fun RowItems(nombre: String) {
    Row() {
        Text(nombre)
    }
}

@Composable
fun consultaPersonasScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Consulta De Personas")}
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { navHostController.navigate("ConsultaOcupaciones") }) {
                Text(text = "Ocupaciones")
            }

            val listaNombres = listOf<String>("Maria", "Jose", "Pedro", "Juan")

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(listaNombres) { nombre ->
                    RowItems(nombre = nombre)
                }
            }
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { navHostController.navigate("RegistroPersonas") }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                },
                floatingActionButtonPosition = FabPosition.End
            ) {

            }

        }
    }

}

@Composable
fun RegistroPersonasScreen(navHostController: NavHostController) {
    var nombre by rememberSaveable() {
        mutableStateOf("")
    }

    var email by rememberSaveable() {
        mutableStateOf("")
    }

    var salario by rememberSaveable() {
        mutableStateOf("")
    }

    val listaOcupaciones =
        listOf<String>("Profesor", "Ingeniero", "Doctor", "Herrero", "Carpintero")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Registro De Personas")}
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nombres")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Email")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            OcupacionesSpinner(ocupacion = listaOcupaciones)

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = salario,
                onValueChange = { salario = it},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Salario")
                }
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = { navHostController.navigateUp() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Guarduar")
            }
        }
    }

}

@Composable
fun OcupacionesSpinner(ocupacion: List<String>) {

    var ocupacionText by remember {
        mutableStateOf("")
    }
    var expended by remember {
        mutableStateOf(false)
    }
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(Modifier
            .fillMaxWidth()
            .clickable {
                expended = !expended
            }
            .padding(8.dp)
        ) {
            Text(text = ocupacionText, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(
                expanded = expended,
                onDismissRequest = { expended = false }
            ) {
                ocupacion.forEach { ocupacion ->
                    DropdownMenuItem(onClick = {

                        expended = false
                        ocupacionText = ocupacion.toString()
                        selectedOcupacion = ocupacion
                    }) {
                        Text(text = ocupacion.toString())
                    }
                }

            }
        }
    }
}

@Composable
fun RegistroOcupacionesScreen(navHostController: NavHostController) {

    var person by rememberSaveable() {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Registro De Ocupaciones")}
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = person,
                onValueChange = { person = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nombre")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Build, contentDescription = null)
                }
            )

            Spacer(modifier = Modifier.height(200.dp))

            Button(
                onClick = { navHostController.navigateUp() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Guarduar")
            }
        }
    }
}

@Composable
fun consultaOcupacionesScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Consulta De Ocupaciones")}
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { navHostController.navigate("ConsultaPersonas") }) {
                Text(text = "Personas")
            }

            val listaOcupaciones =
                listOf<String>("Profesor", "Ingeniero", "Doctor", "Herrero", "Carpintero")

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(listaOcupaciones) { nombre ->
                    RowItems(nombre = nombre)
                }
            }
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { navHostController.navigate("RegistroOcupaciones") }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                },
                floatingActionButtonPosition = FabPosition.End
            ) {

            }

        }
    }

}

















