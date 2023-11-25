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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

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
        }?: println("Cabecera es nulo")

        // se hara la peticion a la base de datos y regresara imprimiendo el cuadro

        


        regreso.setOnClickListener(){
            val nuevofragmen = palabra()
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()
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