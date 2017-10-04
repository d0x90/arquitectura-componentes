package com.pucp.diegocampos.firebasepruebas.ui.adapter

import android.content.Context
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_usuario.view.*

/**
 * Created by desarrollo on 04/10/2017.
 */
class UsuarioViewHolder(itemView : View, itemClick : UsuarioClickCallback) : RecyclerView.ViewHolder(itemView)
{
        var itemClick = itemClick

        fun bind(usuario : UsuarioEntity, context: Context)
        {
            itemView.usernameTextView.text = usuario.username
            Picasso.with(context).load(usuario.image).into(itemView.imageImageView)
            itemView.setOnClickListener { itemClick.onItemClick(usuario) }
        }
}