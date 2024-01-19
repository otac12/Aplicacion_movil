package com.example.lsm_app

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class sustantivos : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sustantivos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val regreso: ImageButton = view.findViewById(R.id.regresar)
        val cabecera = arguments?.getString("cabecera")
        val layout: LinearLayout = view.findViewById(R.id.dele)
        val titulo: TextView = view.findViewById(R.id.cabecera)

        val meses: ImageButton = view.findViewById(R.id.meses)
        val semanas: ImageButton = view.findViewById(R.id.semanas)
        val familia: ImageButton = view.findViewById(R.id.familia)
        val animales: ImageButton = view.findViewById(R.id.animales)
        val frutas: ImageButton = view.findViewById(R.id.frutas)

        val bundle = Bundle()
        val nuevofragmen = lista()

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


        regreso.setOnClickListener(){
            val nuevofragmen = palabra()
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()
        }

        meses.setOnClickListener(){
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Meses")
            nuevofragmen.arguments = bundle
        }

        semanas.setOnClickListener(){
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Semana")
            nuevofragmen.arguments = bundle
        }

        familia.setOnClickListener(){
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Familia")
            nuevofragmen.arguments = bundle
        }
        animales.setOnClickListener(){
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Animales")
            nuevofragmen.arguments = bundle
        }
        frutas.setOnClickListener(){
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Frutas")
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