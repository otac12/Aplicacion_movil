package com.example.lsm_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface PalabraDao {
    @Query("SELECT * FROM Palabras WHERE categoria=:categ")
    suspend fun palabras(categ:String):List<Entidad>

    @Insert
    suspend fun ingresarpalabra(palabra:Entidad)
}