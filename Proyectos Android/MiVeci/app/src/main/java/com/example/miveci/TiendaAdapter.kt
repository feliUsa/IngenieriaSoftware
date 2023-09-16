package com.example.miveci

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TiendaAdapter(private var tiendas: List<Tienda>) : RecyclerView.Adapter<TiendaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.tienda_nombre)
        val ciudadTextView: TextView = itemView.findViewById(R.id.tienda_ciudad)
    }

    // Agrega un método para actualizar la lista de tiendas
    fun actualizarLista(nuevaLista: List<Tienda>) {
        tiendas = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_tienda, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tienda = tiendas[position]
        holder.nombreTextView.text = tienda.nombre
        holder.ciudadTextView.text = "${tienda.ciudad}, ${tienda.localidad}"
    }

    override fun getItemCount(): Int {
        return tiendas.size
    }
}

