package com.example.miveci

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mensaje_hora()
        movimientoBurbujas()
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
        val intent = Intent(this, tiendasActivity::class.java)
        startActivity(intent)
    }

    //funcion para llamar a la actividad de mapa
    fun callMapActivity(view: View) {
        mapUuU()
    }
    fun mapUuU(){
        val intent = Intent(this, mapActivity::class.java)
        startActivity(intent)
    }
    //Funcion para llamar a la actividad de configuracion
    fun callConfigActivity(view: View) {
        configUuU()
    }
    fun configUuU(){
        val intent = Intent(this, configActivity::class.java)
        startActivity(intent)
    }
    //Animacion Movimiento burbujas
    private fun movimientoBurbujas(){
        val bubble1 = findViewById<ImageView>(R.id.burbuja1)
        val bubble2 = findViewById<ImageView>(R.id.burbuja2)
        val bubble3 = findViewById<ImageView>(R.id.burbuja3)
        val bubble4 = findViewById<ImageView>(R.id.burbuja4)

        val bubble1Animator = ObjectAnimator.ofFloat(bubble1, "translationY", 0f, 100f)
        bubble1Animator.duration = 1000
        bubble1Animator.repeatCount = ValueAnimator.INFINITE
        bubble1Animator.repeatMode = ValueAnimator.REVERSE
        bubble1Animator.start()

        val bubble2Animator = ObjectAnimator.ofFloat(bubble2, "translationY", 0f, -100f)
        bubble2Animator.duration = 1000
        bubble2Animator.repeatCount = ValueAnimator.INFINITE
        bubble2Animator.repeatMode = ValueAnimator.REVERSE
        bubble2Animator.start()

        val bubble3Animator = ObjectAnimator.ofFloat(bubble3, "translationY", 0f, -100f)
        bubble3Animator.duration = 1000
        bubble3Animator.repeatCount = ValueAnimator.INFINITE
        bubble3Animator.repeatMode = ValueAnimator.REVERSE
        bubble3Animator.start()

        val bubble4Animator = ObjectAnimator.ofFloat(bubble4, "translationY", 0f, 100f)
        bubble4Animator.duration = 1000
        bubble4Animator.repeatCount = ValueAnimator.INFINITE
        bubble4Animator.repeatMode = ValueAnimator.REVERSE
        bubble4Animator.start()



    }
}