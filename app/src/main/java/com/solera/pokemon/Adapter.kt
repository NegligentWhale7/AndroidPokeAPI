package com.solera.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val characters: List<Characters>):RecyclerView.Adapter<Adapter.ViewHolder> (){

    private var filtered: MutableList<Characters> = characters.toMutableList()

    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val name : TextView = view.findViewById(R.id.tvName)
        val url : TextView = view.findViewById(R.id.tvUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = filtered[position]
        holder.name.text = character.name
        holder.url.text = character.url
    }

    override fun getItemCount(): Int = filtered.size

    fun filter(query: String)
    {
        filtered = if(query.isEmpty()) characters.toMutableList()
        else characters.filter { it.name.contains(query, ignoreCase = true)}.toMutableList()
        notifyDataSetChanged()
    }
}