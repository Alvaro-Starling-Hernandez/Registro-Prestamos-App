package com.example.registroprestamos.ui.persona

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroprestamos.data.PersonasRepository
import com.example.registroprestamos.model.Persona
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonaViewModel @Inject constructor(
    val personasRepository: PersonasRepository
) : ViewModel() {

    var id by mutableStateOf(0)
    var nombres by mutableStateOf("")
    var email by mutableStateOf("")
    var ocupacion by mutableStateOf(0)
    var salario by mutableStateOf("")

    var personas = personasRepository.getLista()
    private set

    fun Guardar(){
        viewModelScope.launch {
            personasRepository.insertar(
                Persona(
                    personaId = id,
                    nombres = nombres,
                    email = email,
                    ocupacionId = ocupacion,
                    salario = salario.toFloat()
                )
            )
        }
    }
}