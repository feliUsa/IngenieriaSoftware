    package com.example.miveci.tenderos

    import android.app.ProgressDialog
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.util.Log
    import android.view.View
    import android.view.animation.TranslateAnimation
    import android.widget.Button
    import android.widget.EditText
    import android.widget.Toast
    import androidx.core.content.ContentProviderCompat.requireContext
    import com.android.volley.AuthFailureError
    import com.android.volley.Request
    import com.android.volley.RequestQueue
    import com.android.volley.Response
    import com.android.volley.VolleyError
    import com.android.volley.toolbox.JsonObjectRequest
    import com.android.volley.toolbox.StringRequest
    import com.android.volley.toolbox.Volley
    import com.example.miveci.R
    import org.json.JSONException
    import org.json.JSONObject
    import java.io.File
    import java.io.FileOutputStream
    import java.io.OutputStreamWriter

    class AgregarTiendasTenderos : AppCompatActivity(){

        private lateinit var tnombre: EditText
        private lateinit var tdescripcion: EditText
        private lateinit var ttipo: EditText
        private lateinit var tciudad: EditText
        private lateinit var tlocalidad: EditText
        private lateinit var tlatitud: EditText
        private lateinit var tlongitud: EditText
        private lateinit var ttelefono: EditText
        private lateinit var btnRegistrar: Button



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_agregar_tiendas_tenderos)
            moverImagen()
            moverCuadros()

            tnombre = findViewById(R.id.edittextNombretienda)
            tdescripcion = findViewById(R.id.editTextDescripciontienda)
            ttipo = findViewById(R.id.editText2)
            tciudad = findViewById(R.id.editText3)
            tlocalidad = findViewById(R.id.editText4)
            tlatitud = findViewById(R.id.editText5)
            tlongitud = findViewById(R.id.editText6)
            ttelefono = findViewById(R.id.editText7)
            btnRegistrar = findViewById(R.id.button)

            btnRegistrar.setOnClickListener {
                ejecutarServicio("http://192.168.56.1:80/TiendasLista/insertar_tienda.php")

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
                    parametros["descripcion"] = tdescripcion.text.toString()
                    parametros["tipp"] = ttipo.text.toString()
                    parametros["ciudad"] = tciudad.text.toString()
                    parametros["localidad"] = tlocalidad.text.toString()
                    parametros["latitud"] = tlatitud.text.toString()
                    parametros["longitud"] = tlongitud.text.toString()
                    parametros["telefono"] = ttelefono.text.toString()
                    return parametros
                }
            }
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(stringRequest)
        }







        private fun moverImagen(){
            val mercado = findViewById<View>(R.id.mercado)
            val translateAnimation = TranslateAnimation(
                150f,
                0f,
                0f,
                0f
            )
            translateAnimation.duration = 10000
            mercado.startAnimation(translateAnimation)
        }

        private fun moverCuadros(){
            val mercado = findViewById<View>(R.id.relativeLayout3)
            val mercado1 = findViewById<View>(R.id.relativeLayout4)
            val translateAnimation = TranslateAnimation(
                1000f,
                0f,
                0f,
                0f
            )
            translateAnimation.duration = 1000
            mercado.startAnimation(translateAnimation)

            val translateAnimation2 = TranslateAnimation(
                -1000f,
                0f,
                0f,
                0f
            )
            translateAnimation2.duration = 1000
            mercado1.startAnimation(translateAnimation2)
        }







    }





