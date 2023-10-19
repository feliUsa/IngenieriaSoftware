package com.example.miveci.tenderos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import com.example.miveci.R

class Inicio_Sesion_Tenderos : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion_tenderos)
        moverComida()
        moverCuadrosTexto()
    }
    override fun onResume() {
        super.onResume()
        moverComida()
        moverCuadrosTexto()
    }

    fun registroTenderos(view: View){
        val intent = Intent(this, Registro_Tenderos::class.java)
        startActivity(intent)
    }
    fun iniciaSesionTenderos(view: View){
        val intent = Intent(this, MainActivity_tenderos::class.java)
        startActivity(intent)
    }

    private fun moverComida(){
        val comidaImageView = findViewById<View>(R.id.comida)

        val translateAnimation = TranslateAnimation(
            0f,
            0f,
            -5000f,
            0f
        )
        val rotateAnimation = RotateAnimation(
            0f,
            1800f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.duration = 100000
        translateAnimation.duration = 3000
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(translateAnimation)
        translateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                comidaImageView.startAnimation(rotateAnimation)
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        comidaImageView.startAnimation(animationSet)
    }
    private fun moverCuadrosTexto(){
        val relative1 = findViewById<View>(R.id.relative1)
        val relative2 = findViewById<View>(R.id.layoutEmailPasswTenderos)
        val relative3 = findViewById<View>(R.id.BotonInicioSesionTenderos)
        val relative4 = findViewById<View>(R.id.trasladoRegistroTenderos)

        val translateAnimation1 = TranslateAnimation(
            -5000f,
            0f,
            0f,
            0f
        )

        val translateAnimation2 = TranslateAnimation(
            5000f,
            0f,
            0f,
            0f
        )
        translateAnimation1.duration = 3000
        translateAnimation2.duration = 3000

        relative1.startAnimation(translateAnimation1)
        relative3.startAnimation(translateAnimation1)
        relative4.startAnimation(translateAnimation2)
        relative2.startAnimation(translateAnimation2)

    }

}