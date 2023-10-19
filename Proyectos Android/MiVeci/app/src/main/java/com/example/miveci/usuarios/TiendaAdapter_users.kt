package com.example.miveci.usuarios

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miveci.R


class TiendaAdapter_users(
    private var tiendaUsers: List<Tienda_users>,
    private val context: Context
) : RecyclerView.Adapter<TiendaAdapter_users.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.tienda_nombre)
        val ciudadTextView: TextView = itemView.findViewById(R.id.tienda_ciudad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_tienda_users, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tienda = tiendaUsers[position]
        holder.nombreTextView.text = tienda.nombre
        holder.ciudadTextView.text = "${tienda.ciudad}, ${tienda.localidad}"

        holder.itemView.setOnClickListener {
            mostrarDialogoInformacionTienda(tienda)
        }
    }

    override fun getItemCount(): Int {
        return tiendaUsers.size
    }

    private fun mostrarDialogoInformacionTienda(tiendaUsers: Tienda_users) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(tiendaUsers.nombre)
        builder.setMessage("Descripción: ${tiendaUsers.descripcion}\nNúmero de contacto: ${tiendaUsers.numeroContacto}")
        builder.setPositiveButton("Ver en el mapa") { _, _ ->
            // Navega a la actividad del mapa centrada en esta tienda y realiza el zoom
            val intent = Intent(context, mapActivity_users::class.java)
            intent.putExtra("lat", tiendaUsers.latitud)
            intent.putExtra("lng", tiendaUsers.longitud)
            intent.putExtra("zoom", true)
            context.startActivity(intent)
        }
        builder.setNegativeButton("Cerrar", null)
        builder.show()
    }

    // Agrega un método para actualizar la lista de tiendas
    fun actualizarLista(nuevaLista: List<Tienda_users>) {
        tiendaUsers = nuevaLista
        notifyDataSetChanged()
    }
}

