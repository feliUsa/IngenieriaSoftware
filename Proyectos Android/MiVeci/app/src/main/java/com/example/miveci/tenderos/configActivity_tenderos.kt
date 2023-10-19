package com.example.miveci.tenderos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.example.miveci.R
import com.example.miveci.usuarios.MainActivity_users

class configActivity_tenderos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_tenderos)
        moverComida()
    }

    fun cerrarSesion(view: View) {
        val intent = Intent(this, MainActivity_users::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun moverComida(){

        val comidaIdk = findViewById<View>(R.id.comidaIdk)

        val rotateAnimation = RotateAnimation(
            0f,
            1800f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.duration = 100000
        comidaIdk.startAnimation(rotateAnimation)
    }


}