package com.example.registroprestamos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personas")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val personaId: Int = 0,
    val nombres: String,
    val email: String,
    val ocupacionId: Int,
    val salario: Float
)