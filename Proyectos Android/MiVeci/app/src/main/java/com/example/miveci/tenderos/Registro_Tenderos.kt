package com.example.miveci.tenderos

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.miveci.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern

class Registro_Tenderos : AppCompatActivity() {

    private lateinit var tnombre: EditText
    private lateinit var tnombreusuario: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnRegistrar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_tenderos)
        val imageView = findViewById<ImageView>(R.id.imageView)
        animateImageView(imageView)
        moverCuadrosTexto()

        tnombre = findViewById(R.id.nombreTenderos)
        tnombreusuario = findViewById(R.id.usuarioTenderos)
        email = findViewById(R.id.Email_Tenderos)
        password = findViewById(R.id.Password_Tenderos)
        btnRegistrar = findViewById(R.id.BotonRegistroSesionTenderos)

        btnRegistrar.setOnClickListener {
            ejecutarServicio("http://192.168.56.1:80/TiendasLista/Tenderos.php")
        }
    }

    fun ejecutarServicio(URL: String) {
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            URL,
            Response.Listener<String> { response ->
                Toast.makeText(applicationContext, "Operacion Exitosa", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                Log.e("ERROR", "Error en la solicitud Volley", error)
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros = HashMap<String, String>()
                parametros["nombre"] = tnombre.text.toString()
                parametros["usuario"] = tnombreusuario.text.toString() // Nuevo campo usuario
                parametros["email"] = email.text.toString() // Nuevo campo email
                parametros["contra"] = password.text.toString() // Nuevo campo contraseña
                return parametros
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }


    private fun animateImageView(imageView: ImageView) {
        val fadeInAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
        fadeInAnimator.duration = 1000
        fadeInAnimator.start()
    }

    private fun moverCuadrosTexto(){
        val relative1 = findViewById<View>(R.id.layoutEmailPasswTenderos)
        val relative2 = findViewById<View>(R.id.BotonRegistroSesionTenderos)


        val translateAnimation1 = TranslateAnimation(
            0f,
            0f,
            -5000f,
            0f
        )

        val translateAnimation2 = TranslateAnimation(
            0f,
            0f,
            5000f,
            0f
        )
        translateAnimation1.duration = 2500
        translateAnimation2.duration = 2500

        relative1.startAnimation(translateAnimation1)
        relative2.startAnimation(translateAnimation2)

    }









}