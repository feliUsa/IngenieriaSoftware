package com.example.miveci.usuarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.TextView
import com.example.miveci.R
import java.util.Calendar

class MainActivity_users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_users)
        mensaje_hora()
        movImagen()
        moveCuadros()
    }

    override fun onResume() {
        super.onResume()
        moveCuadros()
        movImagen()
    }

    //Funcion para mostrar mensaje segun hora por pantalla
    private fun mensaje_hora(){
        val Mensaje_de_saludo = findViewById<TextView>(R.id.Mensaje_de_saludo)
        val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val mensaje = when (hora) {
            in 5..11 -> "Buenos días Veci"
            in 12..17 -> "Buenas tardes Veci"
            else -> "Buenas noches Veci"
        }
        Mensaje_de_saludo.text = mensaje
    }

    //funcion para llamar a la actividad de tiendas
    fun callTienditasActivity(view: View) {
        tiendasUuU()
    }
    fun tiendasUuU(){
        val intent = Intent(this, tiendasActivity_users::class.java)
        startActivity(intent)
    }

    //funcion para llamar a la actividad de mapa
    fun callMapActivity(view: View) {
        mapUuU()
    }
    fun mapUuU(){
        val intent = Intent(this, mapActivity_users::class.java)
        startActivity(intent)
    }
    //Funcion para llamar a la actividad de configuracion
    fun callConfigActivity(view: View) {
        configUuU()
    }
    fun configUuU(){
        val intent = Intent(this, configActivity_users::class.java)
        startActivity(intent)
    }

    private fun movImagen(){
        val imag1 = findViewById<View>(R.id.imag1)

        val translateAnimation = TranslateAnimation(
            1000f,
            0f,
            0f,
            0f
        )
        translateAnimation.duration = 5000

        imag1.startAnimation(translateAnimation)
    }
    private fun moveCuadros(){
        val imag1 = findViewById<View>(R.id.relativeLayout)
        val imag2 = findViewById<View>(R.id.Linear1)

        val translateAnimation = TranslateAnimation(
            2500f,
            0f,
            0f,
            0f
        )

        val translateAnimation2 = TranslateAnimation(
            -2500f,
            0f,
            0f,
            0f
        )
        translateAnimation.duration = 1000
        translateAnimation2.duration = 1000

        imag1.startAnimation(translateAnimation)
        imag2.startAnimation(translateAnimation2)
    }

}