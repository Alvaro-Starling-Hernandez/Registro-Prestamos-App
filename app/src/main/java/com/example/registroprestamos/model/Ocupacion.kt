package com.example.registroprestamos.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Ocupaciones")
@Parcelize
data class Ocupacion(
    @PrimaryKey(autoGenerate = true)
    val ocupacionId: Int = 0,
    val nombre: String
): Parcelable