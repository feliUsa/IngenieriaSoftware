package com.example.miveci

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TiendaAdapter(
    private var tiendas: List<Tienda>,
    private val context: Context
) : RecyclerView.Adapter<TiendaAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.tienda_nombre)
        val ciudadTextView: TextView = itemView.findViewById(R.id.tienda_ciudad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_tienda, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tienda = tiendas[position]
        holder.nombreTextView.text = tienda.nombre
        holder.ciudadTextView.text = "${tienda.ciudad}, ${tienda.localidad}"

        holder.itemView.setOnClickListener {
            mostrarDialogoInformacionTienda(tienda)
        }
    }

    override fun getItemCount(): Int {
        return tiendas.size
    }

    private fun mostrarDialogoInformacionTienda(tienda: Tienda) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(tienda.nombre)
        builder.setMessage("Descripción: ${tienda.descripcion}\nNúmero de contacto: ${tienda.numeroContacto}")
        builder.setPositiveButton("Ver en el mapa") { _, _ ->
            // Navega a la actividad del mapa centrada en esta tienda y realiza el zoom
            val intent = Intent(context, mapActivity::class.java)
            intent.putExtra("lat", tienda.latitud)
            intent.putExtra("lng", tienda.longitud)
            intent.putExtra("zoom", true) // Agregar una bandera para realizar el zoom
            context.startActivity(intent)
        }
        builder.setNegativeButton("Cerrar", null)
        builder.show()
    }

    // Agrega un método para actualizar la lista de tiendas
    fun actualizarLista(nuevaLista: List<Tienda>) {
        tiendas = nuevaLista
        notifyDataSetChanged()
    }
}

