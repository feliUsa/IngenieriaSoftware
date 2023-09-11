package com.example.miveci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mensaje_hora()
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
}