package com.pucp.diegocampos.firebasepruebas.repository

import android.arch.lifecycle.LiveData
import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity

/**
 * Created by desarrollo on 04/10/2017.
 */
interface  UsuarioRepository {

    fun obtenerUsuarios() : LiveData<List<UsuarioEntity>>
    fun obtenerUsuario(user_id : Int) : LiveData<UsuarioEntity>
    fun insertarUsuario(usuario : UsuarioEntity)
    fun editarUsuario(usuario: UsuarioEntity)
    fun eliminarUsuario(user_id : Int )

}