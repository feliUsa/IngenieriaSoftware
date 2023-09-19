package com.example.miveci

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class tiendasActivity : AppCompatActivity() {

    private lateinit var spinnerLocalidad: Spinner
    private lateinit var spinnerCiudad: Spinner
    private lateinit var tiendasCompleta: List<Tienda>
    private lateinit var tiendas: List<Tienda>
    private lateinit var adapter: TiendaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiendas)
        movimientoBurbujas()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Leer los datos de tiendas desde el archivo
        tiendas = readTiendasFromFile("tiendas.txt")
        tiendasCompleta = tiendas.toList() // Copia de seguridad de todas las tiendas

        // Configurar el adaptador inicial para mostrar todas las tiendas
        adapter = TiendaAdapter(tiendas,this)
        recyclerView.adapter = adapter

        // Obtener referencias a los Spinners
        spinnerLocalidad = findViewById(R.id.spinnerLocalidad)
        spinnerCiudad = findViewById(R.id.spinnerCiudad)

        // Configurar adaptadores para los Spinners
        val localidades = obtenerLocalidades().toMutableList()
        val localidadAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, localidades)
        localidadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLocalidad.adapter = localidadAdapter

        val ciudades = obtenerCiudades().toMutableList()
        ciudades.add(0, "Todo")
        val ciudadAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudades)
        ciudadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCiudad.adapter = ciudadAdapter

        // Leer los datos de tiendas desde el archivo
        tiendas = readTiendasFromFile("tiendas.txt")

        // Configurar el adaptador inicial para mostrar todas las tiendas
        adapter = TiendaAdapter(tiendas,this)
        recyclerView.adapter = adapter

        // Agregar listeners para los Spinners
        spinnerLocalidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Filtrar las tiendas por localidad seleccionada
                val localidadSeleccionada = parent.getItemAtPosition(position).toString()
                val tiendasFiltradas = filtrarTiendasPorLocalidad(localidadSeleccionada)
                adapter.actualizarLista(tiendasFiltradas)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerCiudad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val ciudadSeleccionada = parent.getItemAtPosition(position).toString()
                if (ciudadSeleccionada == "Todo") {
                    adapter.actualizarLista(tiendasCompleta) // Mostrar todas las tiendas sin filtrar
                } else {
                    val tiendasFiltradas = filtrarTiendasPorCiudad(ciudadSeleccionada)
                    adapter.actualizarLista(tiendasFiltradas)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }
    }
    private fun readTiendasFromFile(fileName: String): List<Tienda> {
        val tiendas = mutableListOf<Tienda>()
        try {
            val inputStream = resources.openRawResource(R.raw.tiendas)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val parts = line?.split(",")
                if (parts != null && parts.size == 7) {
                    val nombre = parts[0]
                    val ciudad = parts[3]
                    val localidad = parts[4]
                    val descripcion = parts[5]
                    val numeroContacto = parts[6]
                    val latitud = parts[1].toDouble()
                    val longitud = parts[2].toDouble()
                    tiendas.add(Tienda(nombre, ciudad, localidad, descripcion, numeroContacto, latitud, longitud))
                }
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return tiendas
    }


    // Funciones para filtrar tiendas por localidad y ciudad
    private fun filtrarTiendasPorLocalidad(localidad: String): List<Tienda> {
        return tiendas.filter { it.localidad == localidad }
    }

    private fun filtrarTiendasPorCiudad(ciudad: String): List<Tienda> {
        return tiendas.filter { it.ciudad == ciudad }
    }

    // Obtener una lista de todas las localidades únicas de las tiendas
    private fun obtenerLocalidades(): List<String> {
        return tiendas.map { it.localidad }.distinct()
    }

    // Obtener una lista de todas las ciudades únicas de las tiendas
    private fun obtenerCiudades(): List<String> {
        return tiendas.map { it.ciudad }.distinct()
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