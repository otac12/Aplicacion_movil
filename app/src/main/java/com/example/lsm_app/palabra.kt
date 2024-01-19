package com.example.lsm_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class palabra : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_palabra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

        val sustan:ImageButton = view.findViewById(R.id.sustantivos)
        val tiempo:ImageButton = view.findViewById(R.id.tiempos)
        val interrogante:ImageButton = view.findViewById(R.id.interrogantes)
        val verbos:ImageButton = view.findViewById(R.id.verbos)
        val adjetivos:ImageButton = view.findViewById(R.id.adjetivos)
        val respuestas:ImageButton = view.findViewById(R.id.resfrec)

        val bundle = Bundle()
        val nuevofragmen = lista()

        sustan.setOnClickListener(){

            val frag = sustantivos()
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, frag)
            transaction.commit()

            bundle.putString("cabecera", "Sustantivos")
            frag.arguments = bundle

        }

        tiempo.setOnClickListener(){

            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Tiempos")
            nuevofragmen.arguments = bundle

        }

        interrogante.setOnClickListener(){

            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Interrogantes")
            nuevofragmen.arguments = bundle

        }

        verbos.setOnClickListener(){

            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Verbos")
            nuevofragmen.arguments = bundle

        }

        adjetivos.setOnClickListener(){

            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Adjetivos")
            nuevofragmen.arguments = bundle

        }

        respuestas.setOnClickListener(){

            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.frameLayout, nuevofragmen)
            transaction.commit()

            bundle.putString("cabecera", "Respuestas Frecuentes")
            nuevofragmen.arguments = bundle

        }

    }
}