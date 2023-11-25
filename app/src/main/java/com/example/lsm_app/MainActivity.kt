package com.example.lsm_app


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lsm_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Remplazar(menu())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.menu->Remplazar(menu())
                R.id.conexion->Remplazar(conexion())
                R.id.deletreo->Remplazar(deletreo())
                R.id.palabra->Remplazar(palabra())

                else -> {

                }
            }
            true
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun Remplazar(fragmento: Fragment){
        val fragmentomanager = supportFragmentManager
        val fragmentotransaction = fragmentomanager.beginTransaction()
        fragmentotransaction.replace(R.id.frameLayout,fragmento)
        fragmentotransaction.commit()
    }
}