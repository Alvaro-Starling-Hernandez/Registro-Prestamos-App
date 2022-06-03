package com.example.registroprestamos.data

import com.example.registroprestamos.model.Ocupacion
import com.example.registroprestamos.model.Persona
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonasRepository @Inject constructor(
    val personaDao: PersonaDao
){
    suspend fun insertar(persona: Persona){
        personaDao.insertar(persona)
    }

    suspend fun eliminar(persona: Persona){
        personaDao.eliminar(persona)
    }

    fun buscar(personaId: Int): Flow<List<Persona>> {
        return personaDao.buscar(personaId)
    }

    fun getLista(): Flow<List<Persona>> {
        return personaDao.getLista()
    }
}