package com.example.lsm_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(
    entities = [Entidad::class],
    version = 1,
    exportSchema = false
)
abstract class PalabraDB: RoomDatabase() {
    abstract fun palabradao():PalabraDao


    companion object {
        @Volatile
        private var INSTANCE: PalabraDB? = null

        fun getDatabase(context: Context): PalabraDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PalabraDB::class.java,
                    "palabra_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Insertar datos iniciales cuando la base de datos se crea por primera vez
                GlobalScope.launch(Dispatchers.IO) {
                    val palabraDao = INSTANCE?.palabradao()

                    palabraDao?.ingresarpalabra(Entidad(palabra="lunes",categoria="semana", frame = 3))
                    palabraDao?.ingresarpalabra(Entidad(palabra="martes",categoria="semana", frame = 3))
                    palabraDao?.ingresarpalabra(Entidad(palabra="miercoles",categoria="semana", frame = 3))
                    palabraDao?.ingresarpalabra(Entidad(palabra="jueves",categoria="semana", frame = 3))
                    palabraDao?.ingresarpalabra(Entidad(palabra="viernes",categoria="semana", frame = 3))
                    palabraDao?.ingresarpalabra(Entidad(palabra="sabado",categoria="semana", frame = 3))
                    palabraDao?.ingresarpalabra(Entidad(palabra="domingo",categoria="semana", frame = 3))

                    // Agregar más inserciones según sea necesario
                }
            }
        }
    }
}