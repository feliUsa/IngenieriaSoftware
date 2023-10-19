package com.example.miveci.tenderos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import com.example.miveci.R

class MainActivity_tenderos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tenderos)
        movImagen()
        movCuadros()
    }
    fun Agregartiendas(view: View){
        val intent = Intent(this, AgregarTiendasTenderos::class.java)
        startActivity(intent)
    }
    fun ConfigTenderos(view: View){
        val intent = Intent(this, configActivity_tenderos::class.java)
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

    private fun movCuadros(){
        val imag1 = findViewById<View>(R.id.layout)

        val translateAnimation = TranslateAnimation(
            -1000f,
            0f,
            0f,
            0f
        )
        translateAnimation.duration = 1000

        imag1.startAnimation(translateAnimation)
    }

}