package com.example.lsm_app

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.os.Handler
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat


class deletreo : Fragment() {

    private lateinit var a: ImageButton
    private lateinit var b: ImageButton
    private lateinit var c: ImageButton
    private lateinit var d: ImageButton
    private lateinit var e: ImageButton
    private lateinit var f: ImageButton
    private lateinit var g: ImageButton
    private lateinit var h: ImageButton
    private lateinit var i: ImageButton
    private lateinit var j: ImageButton
    private lateinit var k: ImageButton
    private lateinit var l: ImageButton
    private lateinit var m: ImageButton
    private lateinit var n: ImageButton
    private lateinit var ne: ImageButton
    private lateinit var o: ImageButton
    private lateinit var p: ImageButton
    private lateinit var q: ImageButton
    private lateinit var r: ImageButton
    private lateinit var s: ImageButton
    private lateinit var t: ImageButton
    private lateinit var u: ImageButton
    private lateinit var v: ImageButton
    private lateinit var w: ImageButton
    private lateinit var x: ImageButton
    private lateinit var y: ImageButton
    private lateinit var z: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deletreo, container, false)
    }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      val deletrear: ImageButton = view.findViewById(R.id.enviar)

      a= view.findViewById(R.id.a)
      b= view.findViewById(R.id.b)
      c= view.findViewById(R.id.c)
      d= view.findViewById(R.id.d)
      e= view.findViewById(R.id.e)
      f= view.findViewById(R.id.f)
      g= view.findViewById(R.id.g)
      h= view.findViewById(R.id.h)
      i= view.findViewById(R.id.i)
      j= view.findViewById(R.id.j)
      k= view.findViewById(R.id.k)
      l= view.findViewById(R.id.l)
      m= view.findViewById(R.id.m)
      n= view.findViewById(R.id.n)
      ne= view.findViewById(R.id.ne)
      o= view.findViewById(R.id.o)
      p= view.findViewById(R.id.p)
      q= view.findViewById(R.id.q)
      r= view.findViewById(R.id.r)
      s= view.findViewById(R.id.s)
      t= view.findViewById(R.id.t)
      u= view.findViewById(R.id.u)
      v= view.findViewById(R.id.v)
      w= view.findViewById(R.id.w)
      x= view.findViewById(R.id.x)
      y= view.findViewById(R.id.y)
      z= view.findViewById(R.id.z)


      a.setOnClickListener{
            val ventana = Previsualizacion()
            val bundle = Bundle()
            bundle.putString("direccion", "a")
            ventana.arguments = bundle
            ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      b.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "b")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      c.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "c")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      d.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "d")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      e.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "e")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      f.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "f")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      g.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "g")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      h.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "h")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      i.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "i")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      j.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "j")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      k.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "k")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      l.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "l")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      m.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "m")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      n.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "n")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      ne.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "ne")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      o.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "o")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      p.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "p")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      q.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "q")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      r.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "r")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      s.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "s")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      t.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "t")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      u.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "u")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      v.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "v")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      w.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "w")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      x.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "x")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      y.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "y")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      z.setOnClickListener{
          val ventana = Previsualizacion()
          val bundle = Bundle()
          bundle.putString("direccion", "z")
          ventana.arguments = bundle
          ventana.show((activity as AppCompatActivity).supportFragmentManager,"ventana")
      }

      deletrear.setOnClickListener {
          val drawable = ContextCompat.getDrawable(requireContext(),R.drawable.sombreado_activo)

          deletrear.background= drawable

          val input: EditText = view.findViewById(R.id.Texto)
          val textoIngresado = input.text.toString()

          val imm: InputMethodManager = input.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

          imm.hideSoftInputFromWindow(view.windowToken, 0)

          Handler().postDelayed({
              deletrear.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
          }, 750 )

          for ((index, caracter) in textoIngresado.withIndex()) {

              Handler().postDelayed({
                  println(caracter)
                  println(index.toLong())
                  cambiarColor(caracter, true)
              }, 750 * (index.toLong()+1))

              Handler().postDelayed({
                  println(caracter)
                  cambiarColor(caracter,false)
              }, 750 * (index.toLong()+2))
          }
        }
      }

    fun cambiarColor(caracter:Char,activo:Boolean){
        val drawable = ContextCompat.getDrawable(requireContext(),R.drawable.sombreado_activo)
        val drawable_inactivo = ContextCompat.getDrawable(requireContext(),R.drawable.sombreado)

        if (activo == true){
            activity?.runOnUiThread {
                    when (caracter) {
                        'a' -> a.background = drawable
                        'b' -> b.background = drawable
                        'c' -> c.background = drawable
                        'd' -> d.background = drawable
                        'e' -> e.background = drawable
                        'f' -> f.background = drawable
                        'g' -> g.background = drawable
                        'h' -> h.background = drawable
                        'i' -> i.background = drawable
                        'j' -> j.background = drawable
                        'k' -> k.background = drawable
                        'l' -> l.background = drawable
                        'm' -> m.background = drawable
                        'n' -> n.background = drawable
                        'ñ' -> ne.background = drawable
                        'o' -> o.background = drawable
                        'p' -> p.background = drawable
                        'q' -> q.background = drawable
                        'r' -> r.background = drawable
                        's' -> s.background = drawable
                        't' -> t.background = drawable
                        'u' -> u.background = drawable
                        'v' -> v.background = drawable
                        'w' -> w.background = drawable
                        'x' -> x.background = drawable
                        'y' -> y.background = drawable
                        'z' -> z.background = drawable

                    }
                }
            }else {
            activity?.runOnUiThread {
                    when (caracter) {
                        'a' -> a.background = drawable_inactivo
                        'b' -> b.background = drawable_inactivo
                        'c' -> c.background = drawable_inactivo
                        'd' -> d.background = drawable_inactivo
                        'e' -> e.background = drawable_inactivo
                        'f' -> f.background = drawable_inactivo
                        'g' -> g.background = drawable_inactivo
                        'h' -> h.background = drawable_inactivo
                        'i' -> i.background = drawable_inactivo
                        'j' -> j.background = drawable_inactivo
                        'k' -> k.background = drawable_inactivo
                        'l' -> l.background = drawable_inactivo
                        'm' -> m.background = drawable_inactivo
                        'n' -> n.background = drawable_inactivo
                        'ñ' -> ne.background = drawable_inactivo
                        'o' -> o.background = drawable_inactivo
                        'p' -> p.background = drawable_inactivo
                        'q' -> q.background = drawable_inactivo
                        'r' -> r.background = drawable_inactivo
                        's' -> s.background = drawable_inactivo
                        't' -> t.background = drawable_inactivo
                        'u' -> u.background = drawable_inactivo
                        'v' -> v.background = drawable_inactivo
                        'w' -> w.background = drawable_inactivo
                        'x' -> x.background = drawable_inactivo
                        'y' -> y.background = drawable_inactivo
                        'z' -> z.background = drawable_inactivo
                      }
                }
            }
        }

}
