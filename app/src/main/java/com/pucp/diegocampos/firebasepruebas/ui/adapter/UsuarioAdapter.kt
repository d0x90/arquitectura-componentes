package com.pucp.diegocampos.firebasepruebas.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pucp.diegocampos.firebasepruebas.R
import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity


/**
 * Created by desarrollo on 04/10/2017.
 */
class UsuarioAdapter(context: Context, usuarioClickCallback: UsuarioClickCallback) : RecyclerView.Adapter<UsuarioViewHolder>() {
    var context = context
    var usuarioClickCallback = usuarioClickCallback
    var mUsuarios = mutableListOf<UsuarioEntity>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UsuarioViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_usuario,parent,false)
        return UsuarioViewHolder(view, usuarioClickCallback)
    }

    override fun getItemCount(): Int {
        return mUsuarios.size
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val actual_user = mUsuarios[position]
        holder.bind(actual_user,context)


    }

    fun swap(lista : List<UsuarioEntity>)
    {
        this.mUsuarios.clear()
        this.mUsuarios.addAll(lista)
        notifyDataSetChanged()
    }





}