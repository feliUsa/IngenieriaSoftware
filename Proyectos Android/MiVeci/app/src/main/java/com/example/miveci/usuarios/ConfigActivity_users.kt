package com.example.miveci.usuarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.example.miveci.R

class configActivity_users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_users)
        movImagen()
    }
    override fun onResume() {
        super.onResume()
        movImagen()
    }

    fun Inicio_Sesion_Tenderos(view: View){
        val intent = Intent(this, com.example.miveci.tenderos.Inicio_Sesion_Tenderos::class.java)
        startActivity(intent)
    }

    private fun movImagen(){
        val comidaImageView = findViewById<View>(R.id.comida)
        val rotateAnimation = RotateAnimation(
            0f,
            1800f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.duration = 100000
        comidaImageView.startAnimation(rotateAnimation)
    }

}