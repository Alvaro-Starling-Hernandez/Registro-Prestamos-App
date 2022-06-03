package com.example.registroprestamos.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registroprestamos.model.Ocupacion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OcupacionRepository @Inject constructor(
    val ocupacionDao: OcupacionDao
)  {

    suspend fun insertar(ocupacion: Ocupacion){
        ocupacionDao.insertar(ocupacion)
    }

    suspend fun eliminar(ocupacion: Ocupacion){
        ocupacionDao.eliminar(ocupacion)
    }

    fun buscar(ocupacionId: Int): Flow<List<Ocupacion>>{
        return ocupacionDao.buscar(ocupacionId)
    }

    fun getLista(): Flow<List<Ocupacion>>{
        return ocupacionDao.getLista()
    }
}