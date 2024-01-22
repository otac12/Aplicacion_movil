package com.example.lsm_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.os.Handler
import androidx.fragment.app.DialogFragment


class Previsualizacion : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_TransparentWithBlackBackground)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_previsualizacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var frame:Int =0

        val imagen = arguments?.getString("direccion")

        arguments?.getInt("frame")?.let {
            frame = it
        }

        val imageView = view?.findViewById<ImageView>(R.id.imageView)

        val id = resources.getIdentifier(imagen + "_persona_1","drawable",requireContext().packageName)

        if(id != 0){
            imageView?.setImageResource(id)
            println(imagen)
            println("se encontro")

        }else{
            println(imagen)
            imageView?.setImageResource(R.drawable.a_persona)
            frame=0
            println("No se encontro")
        }

        when(imagen){
            "a" -> imageView?.setImageResource(R.drawable.a_persona)
            "b" -> imageView?.setImageResource(R.drawable.b_persona)
            "c" -> imageView?.setImageResource(R.drawable.c_persona)
            "d" -> imageView?.setImageResource(R.drawable.d_persona)
            "e" -> imageView?.setImageResource(R.drawable.e_persona)
            "f" -> imageView?.setImageResource(R.drawable.f_persona)
            "g" -> imageView?.setImageResource(R.drawable.g_persona)
            "h" -> imageView?.setImageResource(R.drawable.h_persona)
            "i" -> imageView?.setImageResource(R.drawable.i_persona)
            "j" -> imageView?.setImageResource(R.drawable.j_persona_1)
            "k" -> imageView?.setImageResource(R.drawable.k_persona_1)
            "l" -> imageView?.setImageResource(R.drawable.l_persona)
            "m" -> imageView?.setImageResource(R.drawable.m_persona)
            "n" -> imageView?.setImageResource(R.drawable.n_persona)
            "ne" -> imageView?.setImageResource(R.drawable.nn_persona_1)
            "o" -> imageView?.setImageResource(R.drawable.o_persona)
            "p" -> imageView?.setImageResource(R.drawable.p_persona)
            "q" -> imageView?.setImageResource(R.drawable.q_persona_1)
            "r" -> imageView?.setImageResource(R.drawable.r_persona)
            "s" -> imageView?.setImageResource(R.drawable.s_persona)
            "t" -> imageView?.setImageResource(R.drawable.t_persona)
            "u" -> imageView?.setImageResource(R.drawable.u_persona)
            "v" -> imageView?.setImageResource(R.drawable.v_persona)
            "w" -> imageView?.setImageResource(R.drawable.w_persona)
            "x" -> imageView?.setImageResource(R.drawable.x_persona_1)
            "y" -> imageView?.setImageResource(R.drawable.y_persona)
            "z" -> imageView?.setImageResource(R.drawable.z_persona_1)
        }



        imageView?.let {
            animacion(imagen, it , frame)
        }

        var x = view.findViewById<Button>(R.id.cerrar)
        var repetir = view.findViewById<Button>(R.id.repetir)

        x.setOnClickListener{
            dismiss()
        }

        repetir.setOnClickListener{
            imageView?.let {
                animacion(imagen, it,frame)
            }
        }
    }

    fun animacion(imagen:String?,imageView: ImageView?,frame: Int){

        if(frame == 0){
            if(imagen == "z"){
                for(i in 1..4){
                    val ruta = "z_persona_$i"
                    val num = 250*(i-1)
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                }
            }

            if(imagen == "ne"){
                for(i in 2..5){
                    val ruta = "nn_persona_$i"
                    val num = 100*(i-1)
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                }
                val inicio = 5
                var c=1

                for(i in inicio downTo 1){
                    val ruta = "nn_persona_$i"
                    val num = (100*5)+(100*c)
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())

                    c=c+1
                }

            }

            if(imagen == "j"){
                for(i in 1..6){
                    val ruta = "j_persona_$i"
                    val num = 150*(i-1)
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                }
            }

            if(imagen == "x"){
                for(i in 1..3){
                    val ruta = "x_persona_$i"
                    val num = 150*(i-1)
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                }
            }

            if (imagen == "k"){
                val frame=200
                for(i in 1..3){
                    val ruta = "k_persona_$i"
                    val num = frame*i
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                }
                var inicio = 3
                var c=1

                for(i in inicio downTo 1){
                    val ruta = "k_persona_$i"
                    val num = (frame*3)+(frame*c)
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                    c=c+1
                }

                for(i in 4..6){
                    val ruta = "k_persona_$i"
                    val num = (frame*6)+(frame*(i-3))
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                }

                inicio = 6
                c=1
                println(inicio)
                println(c)
                for(i in inicio downTo 4){
                    val ruta = "k_persona_$i"
                    val num = (frame*9)+(frame*c)
                    println(num)
                    println(i)
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())

                    c=c+1
                }

            }

            if (imagen == "q"){
                val frame=200
                for(i in 1..3){
                    val ruta = "q_persona_$i"
                    val num = frame*i
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                }
                var inicio = 3
                var c=1

                for(i in inicio downTo 1){
                    val ruta = "q_persona_$i"
                    val num = (frame*3)+(frame*c)
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                    c=c+1
                }

                for(i in 4..6){
                    val ruta = "q_persona_$i"
                    val num = (frame*6)+(frame*(i-3))
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())
                }

                inicio = 6
                c=1
                println(inicio)
                println(c)
                for(i in inicio downTo 4){
                    val ruta = "q_persona_$i"
                    val num = (frame*9)+(frame*c)
                    println(num)
                    println(i)
                    val idDrawable = resources.getIdentifier(ruta, "drawable", requireContext().packageName)
                    Handler().postDelayed({
                        imageView?.setImageResource(idDrawable)
                    },num.toLong())

                    c=c+1
                }

            }

        }else{
            for (i in 1 until frame+1){
                val num = 250*(i-1)
                val id = resources.getIdentifier(imagen + "_persona_"+i,"drawable",requireContext().packageName)
                Handler().postDelayed({
                    imageView?.setImageResource(id)
                },num.toLong())
            }
        }

    }

}