package com.example.registroprestamos.ui.ocupacion

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroprestamos.data.OcupacionRepository
import com.example.registroprestamos.model.Ocupacion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    val ocupacionRepository: OcupacionRepository
): ViewModel( ) {

    var nombre by mutableStateOf("")
    var id by mutableStateOf(0)


    var ocupaciones = ocupacionRepository.getLista()
    private set


    fun Guardar(){
        viewModelScope.launch {
            ocupacionRepository.insertar(
                Ocupacion(
                    ocupacionId = id,
                    nombre = nombre
                )
            )
        }
    }


}