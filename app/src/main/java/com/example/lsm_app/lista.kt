package com.example.lsm_app

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.lsm_app.database.PalabraDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class lista : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val regreso:ImageButton = view.findViewById(R.id.regresar)
        val cabecera = arguments?.getString("cabecera")
        val layout:LinearLayout = view.findViewById(R.id.dele)
        val titulo:TextView = view.findViewById(R.id.cabecera)

        titulo.setText(cabecera)

        // se hara la peticion a la base de datos y regresara imprimiendo el cuadro
        cabecera?.let {noNuloCabecera ->
            println("Se imprime la cabecera")
            for (letra in cabecera.toLowerCase()){
                val imagen = ImageView(requireContext())
                val ruta = "$letra"
                val widthInDp = 25
                val heightInDp = 40
                val widthInPixels = requireContext().dpToPx(widthInDp)
                val heightInPixels = requireContext().dpToPx(heightInDp)

                val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                println(ruta)
                imagen.setImageResource(idDrawable)

                val params = RelativeLayout.LayoutParams(
                    widthInPixels,
                    heightInPixels
                )

                imagen.setBackgroundColor(Color.TRANSPARENT)
                imagen.layoutParams = params
                imagen.scaleType = ImageView.ScaleType.FIT_XY
                layout.addView(imagen)

            }


            // se hara la peticion a la base de datos y regresara imprimiendo el cuadro
            lifecycleScope.launch {
                val palabraDB = PalabraDB.getDatabase(requireContext())
                val palabraD = palabraDB.palabradao()

                val resultado = palabraD.palabras(cabecera.toLowerCase())

                println("la longitud de tu lista ${resultado.size}")

                // Esta seccion de codigo es para agregar un Layout 1
                var cont:LinearLayout? = null

                for (i in resultado.indices) {

                    if (i % 3 == 0) {
                        val linearLayoutexistente = view.findViewById<LinearLayout>(R.id.Ventana)
                        val linearLayoutnuevo = LinearLayout(requireContext())

                        val nuevoIdString = "Nivel$i"
                        val nuevoId = resources.getIdentifier(nuevoIdString, "id", requireContext().packageName)

                        linearLayoutnuevo.id = if (nuevoId == 0) View.generateViewId() else nuevoId

                        linearLayoutnuevo.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            requireContext().dpToPx(225)
                        )
                        linearLayoutnuevo.orientation = LinearLayout.HORIZONTAL
                        linearLayoutnuevo.setBackgroundColor(resources.getColor(R.color.grisaseo))

                        linearLayoutexistente.addView(linearLayoutnuevo)

                        cont = view.findViewById<LinearLayout>(linearLayoutnuevo.id)

                        val linearLayoutcreado = view.findViewById<LinearLayout>(linearLayoutnuevo.id)

                        val imagen = resources.getIdentifier(resultado[i].palabra +"_icono", "drawable", requireContext().packageName)

                        val boton = ImageButton(requireContext())
                        val parametros = ViewGroup.MarginLayoutParams(
                            requireContext().dpToPx(130),
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )

                        parametros.setMargins(15,12,1,12)

                        boton.layoutParams = parametros
                        boton.scaleType = ImageView.ScaleType.FIT_XY
                        boton.setImageResource(imagen)
                        boton.setBackgroundResource(R.drawable.sombreado)

                        boton.setOnClickListener(){
                            val ventana = Previsualizacion()
                            val bundle = Bundle()
                            bundle.putString("direccion", "a")
                            ventana.arguments = bundle
                            ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
                        }

                        if (linearLayoutcreado != null) {
                            linearLayoutcreado.addView(boton)
                        } else {
                            // Manejar el caso en el que linearLayoutcre es nulo
                        }
                    } else {
                        val linearLayoutcreado = cont

                        val imagen = resources.getIdentifier(resultado[i].palabra +"_icono", "drawable", requireContext().packageName)

                        val boton = ImageButton(requireContext())
                        val parametros = ViewGroup.MarginLayoutParams(
                            requireContext().dpToPx(130),
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )

                        parametros.setMargins(15,12,1,12)

                        boton.layoutParams = parametros
                        boton.scaleType = ImageView.ScaleType.FIT_XY
                        boton.setImageResource(imagen)
                        boton.setBackgroundResource(R.drawable.sombreado)

                        boton.setOnClickListener(){
                            val ventana = Previsualizacion()
                            val bundle = Bundle()
                            bundle.putString("direccion", "a")
                            ventana.arguments = bundle
                            ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
                        }

                        if (linearLayoutcreado != null) {
                            linearLayoutcreado.addView(boton)
                        } else {
                            // Manejar el caso en el que linearLayoutcre es nulo
                        }
                    }
                }
                // ------ Layout 1



            }
        }

            ?: println("Cabecera es nulo")







        regreso.setOnClickListener(){
            val bundle = Bundle()
            val nuevofragmen = sustantivos()
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Sustantivos")
            nuevofragmen.arguments = bundle
        }

    }

    fun Context.dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}