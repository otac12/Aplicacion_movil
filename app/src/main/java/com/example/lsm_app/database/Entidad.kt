package com.example.lsm_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Palabras")
data class Entidad (
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    val palabra:String,
    val categoria:String,
    val frame: Int
)