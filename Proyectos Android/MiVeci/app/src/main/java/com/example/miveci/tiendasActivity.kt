package com.example.miveci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Leer los datos de tiendas desde el archivo
        tiendas = readTiendasFromFile("tiendas.txt")
        tiendasCompleta = tiendas.toList() // Copia de seguridad de todas las tiendas

        // Configurar el adaptador inicial para mostrar todas las tiendas
        adapter = TiendaAdapter(tiendas)
        recyclerView.adapter = adapter

        // Obtener referencias a los Spinners
        spinnerLocalidad = findViewById(R.id.spinnerLocalidad)
        spinnerCiudad = findViewById(R.id.spinnerCiudad)

        // Configurar adaptadores para los Spinners (debes proporcionar tus propios datos)
        val localidades = obtenerLocalidades().toMutableList()
        localidades.add(0, "Todo")
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
        adapter = TiendaAdapter(tiendas)
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
                // No hacer nada
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
                if (parts != null && parts.size == 5) {
                    val nombre = parts[0]
                    val ciudad = parts[3]
                    val localidad = parts[4]
                    tiendas.add(Tienda(nombre, ciudad, localidad))
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

}