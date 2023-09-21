package com.example.miveci

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class Inicio_Sesion_Tenderos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion_tenderos)
        movimientoBurbujas()
    }

    fun iniciaSesionTenderos(view: View) {
        val intent = Intent(this, MainActivity_tenderos::class.java)
        startActivity(intent)
    }

    fun trasladoPaginaClientes(view: View){
        val intent = Intent(this, Inicio_Sesion_users::class.java)
        startActivity(intent)
    }
    //Animacion Movimiento burbujas
    private fun movimientoBurbujas(){
        val bubble1 = findViewById<ImageView>(R.id.burbuja1)
        val bubble2 = findViewById<ImageView>(R.id.burbuja2)
        val bubble3 = findViewById<ImageView>(R.id.burbuja3)
        val bubble4 = findViewById<ImageView>(R.id.burbuja4)
        val bubble5 = findViewById<ImageView>(R.id.burbuja5)

        val bubble1Animator = ObjectAnimator.ofFloat(bubble1, "translationY", 0f, 50f)
        bubble1Animator.duration = 1000
        bubble1Animator.repeatCount = ValueAnimator.INFINITE
        bubble1Animator.repeatMode = ValueAnimator.REVERSE
        bubble1Animator.start()

        val bubble2Animator = ObjectAnimator.ofFloat(bubble2, "translationY", 0f, -50f)
        bubble2Animator.duration = 1000
        bubble2Animator.repeatCount = ValueAnimator.INFINITE
        bubble2Animator.repeatMode = ValueAnimator.REVERSE
        bubble2Animator.start()

        val bubble3Animator = ObjectAnimator.ofFloat(bubble3, "translationY", 0f, -50f)
        bubble3Animator.duration = 1000
        bubble3Animator.repeatCount = ValueAnimator.INFINITE
        bubble3Animator.repeatMode = ValueAnimator.REVERSE
        bubble3Animator.start()

        val bubble4Animator = ObjectAnimator.ofFloat(bubble4, "translationY", 0f, 50f)
        bubble4Animator.duration = 1000
        bubble4Animator.repeatCount = ValueAnimator.INFINITE
        bubble4Animator.repeatMode = ValueAnimator.REVERSE
        bubble4Animator.start()

        val bubble5Animator = ObjectAnimator.ofFloat(bubble5, "translationY", 0f, -50f)
        bubble5Animator.duration = 1000
        bubble5Animator.repeatCount = ValueAnimator.INFINITE
        bubble5Animator.repeatMode = ValueAnimator.REVERSE
        bubble5Animator.start()



    }

}