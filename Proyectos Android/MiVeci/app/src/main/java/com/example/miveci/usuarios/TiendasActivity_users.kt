package com.example.miveci.usuarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miveci.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class tiendasActivity_users : AppCompatActivity() {

    private lateinit var spinnerLocalidad: Spinner
    private lateinit var spinnerCiudad: Spinner
    private lateinit var tiendasCompleta: List<Tienda_users>
    private lateinit var tiendaUsers: List<Tienda_users>
    private lateinit var adapter: TiendaAdapter_users

    private var ciudadSeleccionada: String = "Ciudad"
    private var localidadSeleccionada: String = "Localidad"
    private var tipoSeleccionado: String = "Tipo"

    override fun onResume() {
        super.onResume()
        movimientoImagen()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiendas_users)
        movimientoImagen()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Leer los datos de tiendas desde el archivo
        tiendaUsers = readTiendasFromFile("tiendas.txt")
        tiendasCompleta = tiendaUsers.toList()

        // Configurar el adaptador inicial para mostrar todas las tiendas
        adapter = TiendaAdapter_users(tiendaUsers,this)
        recyclerView.adapter = adapter

        // Obtener referencias a los Spinners
        spinnerLocalidad = findViewById(R.id.spinnerLocalidad)
        spinnerCiudad = findViewById(R.id.spinnerCiudad)

        // Configurar adaptadores para los Spinners
        val localidades = obtenerLocalidades().toMutableList()
        localidades.add(0, "Localidad")
        val localidadAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, localidades)
        localidadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLocalidad.adapter = localidadAdapter

        val ciudades = obtenerCiudades().toMutableList()
        ciudades.add(0, "Ciudad")
        val ciudadAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudades)
        ciudadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCiudad.adapter = ciudadAdapter

        // Leer los datos de tiendas desde el archivo
        tiendaUsers = readTiendasFromFile("tiendas.txt")

        // Configurar el adaptador inicial para mostrar todas las tiendas
        adapter = TiendaAdapter_users(tiendaUsers,this)
        recyclerView.adapter = adapter


        // Agregar listener para el Spinner de localidades
        spinnerLocalidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                localidadSeleccionada = parent.getItemAtPosition(position).toString()
                aplicarFiltros()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }


        spinnerCiudad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val ciudadSeleccionada = parent.getItemAtPosition(position).toString()
                if (ciudadSeleccionada == "Ciudad") {
                    adapter.actualizarLista(tiendasCompleta)
                } else {
                    val tiendasFiltradas = filtrarTiendasPorCiudad(ciudadSeleccionada)
                    adapter.actualizarLista(tiendasFiltradas)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }

        val spinnerTipo: Spinner = findViewById(R.id.spinnerTipo)

        // Obtener una lista de todos los tipos de tiendas únicos
                val tiposDeTienda = obtenerTiposDeTienda().toMutableList()
                tiposDeTienda.add(0, "Tipo")

                val tipoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposDeTienda)
                tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerTipo.adapter = tipoAdapter

        // Agregar listener para el Spinner de tipo de tienda
        spinnerTipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                tipoSeleccionado = parent.getItemAtPosition(position).toString()
                aplicarFiltros()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }
    }

    private fun aplicarFiltros() {
        val tiendasFiltradas = tiendasCompleta.filter { tienda ->
            (ciudadSeleccionada == "Ciudad" || tienda.ciudad == ciudadSeleccionada) &&
                    (localidadSeleccionada == "Localidad" || tienda.localidad == localidadSeleccionada) &&
                    (tipoSeleccionado == "Tipo" || tienda.tipo == tipoSeleccionado)
        }
        adapter.actualizarLista(tiendasFiltradas)
    }
    private fun readTiendasFromFile(fileName: String): List<Tienda_users> {
        val tiendaUsers = mutableListOf<Tienda_users>()
        try {
            val inputStream = resources.openRawResource(R.raw.tiendas)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val parts = line?.split(";")
                if (parts != null && parts.size == 8) {
                    val nombre = parts[0]
                    val ciudad = parts[3]
                    val localidad = parts[4]
                    val tipo = parts[5]
                    val descripcion = parts[6]
                    val numeroContacto = parts[7]
                    val latitud = parts[1].toDouble()
                    val longitud = parts[2].toDouble()
                    tiendaUsers.add(Tienda_users(nombre, ciudad, localidad, tipo, descripcion, numeroContacto, latitud, longitud))
                }

            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return tiendaUsers
    }


    // Obtener una lista de todos los tipos de tiendas únicos de las tiendas
    private fun obtenerTiposDeTienda(): List<String> {
        return tiendaUsers.map { it.tipo }.distinct()
    }

    // Función para filtrar tiendas por tipo
    private fun filtrarTiendasPorTipo(tipo: String): List<Tienda_users> {
        return tiendaUsers.filter { it.tipo == tipo }
    }

    // Funciones para filtrar tiendas por localidad y ciudad
    private fun filtrarTiendasPorLocalidad(localidad: String): List<Tienda_users> {
        return tiendaUsers.filter { it.localidad == localidad }
    }

    private fun filtrarTiendasPorCiudad(ciudad: String): List<Tienda_users> {
        return tiendaUsers.filter { it.ciudad == ciudad }
    }

    // Obtener una lista de todas las localidades únicas de las tiendas
    private fun obtenerLocalidades(): List<String> {
        return tiendaUsers.map { it.localidad }.distinct()
    }

    // Obtener una lista de todas las ciudades únicas de las tiendas
    private fun obtenerCiudades(): List<String> {
        return tiendaUsers.map { it.ciudad }.distinct()
    }

    private fun movimientoImagen(){
        val mercado = findViewById<View>(R.id.mercado)
        val translateAnimation = TranslateAnimation(
            -100f,
            0f,
            20f,
            0f
        )
        translateAnimation.duration = 10000
        mercado.startAnimation(translateAnimation)
    }


}