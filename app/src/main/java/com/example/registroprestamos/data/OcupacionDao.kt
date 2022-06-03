package com.example.registroprestamos.data

import androidx.room.*
import com.example.registroprestamos.model.Ocupacion
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(ocupacion: Ocupacion)

    @Delete
    suspend fun eliminar(ocupacion: Ocupacion)

    @Query("SELECT * FROM ocupaciones WHERE  ocupacionId =:ocupacionId")
    fun buscar(ocupacionId: Int): Flow<List<Ocupacion>>

    @Query("SELECT * FROM ocupaciones ORDER BY ocupacionId")
    fun getLista(): Flow<List<Ocupacion>>
}