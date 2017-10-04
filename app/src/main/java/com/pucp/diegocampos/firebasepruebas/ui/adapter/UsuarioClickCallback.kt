package com.pucp.diegocampos.firebasepruebas.ui.adapter

import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity

/**
 * Created by desarrollo on 04/10/2017.
 */
interface UsuarioClickCallback
{
    fun onItemClick(usuario : UsuarioEntity)
}