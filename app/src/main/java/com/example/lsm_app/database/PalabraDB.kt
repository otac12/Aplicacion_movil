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

                    val dias = listOf("lunes", "martes", "miercoles", "jueves", "viernes","sabado","domingo");
                    val meses = listOf("enero", "febrero","marzo","abril", "mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre");


                    for(item in dias){
                        palabraDao?.ingresarpalabra(Entidad(palabra=item,categoria="semana", frame = 6))
                    }

                    for(item in meses){

                        if(item=="abril" || item=="marzo"){
                            palabraDao?.ingresarpalabra(Entidad(palabra=item,categoria="meses", frame = 6))
                        }else{
                            palabraDao?.ingresarpalabra(Entidad(palabra=item,categoria="meses", frame = 5))
                        }
                    }

                    palabraDao?.ingresarpalabra(Entidad(palabra="hoy",categoria="tiempos", frame = 3))
                    palabraDao?.ingresarpalabra(Entidad(palabra="ayer",categoria="tiempos", frame = 5))
                    palabraDao?.ingresarpalabra(Entidad(palabra="manana",categoria="tiempos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="ahora",categoria="tiempos", frame = 3))
                    palabraDao?.ingresarpalabra(Entidad(palabra="ahorita",categoria="tiempos", frame = 4))

                    palabraDao?.ingresarpalabra(Entidad(palabra="gato",categoria="animales", frame = 3))
                    palabraDao?.ingresarpalabra(Entidad(palabra="perro",categoria="animales", frame = 5))

                    palabraDao?.ingresarpalabra(Entidad(palabra="manzana",categoria="frutas", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="naranja",categoria="frutas", frame = 7))

                    palabraDao?.ingresarpalabra(Entidad(palabra="papa",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="mama",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="nina",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="nino",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="abuela",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="abuelo",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="hija",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="hijo",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="novia",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="novio",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="amigo",categoria="familia", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="amiga",categoria="familia", frame = 6))

                    palabraDao?.ingresarpalabra(Entidad(palabra="celular",categoria="extras", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="casa",categoria="extras", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="mesa",categoria="extras", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="silla",categoria="extras", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="libro",categoria="extras", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="computadora",categoria="extras", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="avion",categoria="extras", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="coche",categoria="extras", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="cafe",categoria="extras", frame = 6))

                    palabraDao?.ingresarpalabra(Entidad(palabra="ir",categoria="verbos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="comer",categoria="verbos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="buscar",categoria="verbos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="recordar",categoria="verbos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="gustar",categoria="verbos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="querer",categoria="verbos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="conocer",categoria="verbos", frame = 6))

                    palabraDao?.ingresarpalabra(Entidad(palabra="dulce",categoria="adjetivos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="frio",categoria="adjetivos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="caliente",categoria="adjetivos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="grande",categoria="adjetivos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="pequeno",categoria="adjetivos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="delgado",categoria="adjetivos", frame = 6))
                    palabraDao?.ingresarpalabra(Entidad(palabra="gordo",categoria="adjetivos", frame = 6))







                    // Agregar más inserciones según sea necesario
                }
            }
        }
    }
}