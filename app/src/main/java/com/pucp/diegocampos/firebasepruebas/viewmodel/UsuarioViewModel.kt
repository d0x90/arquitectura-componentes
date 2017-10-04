package com.pucp.diegocampos.firebasepruebas.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity
import com.pucp.diegocampos.firebasepruebas.repository.UsuarioRepository
import com.pucp.diegocampos.firebasepruebas.repository.UsuarioRepositoryImpl

/**
 * Created by desarrollo on 04/10/2017.
 */
class UsuarioViewModel(application: Application) : AndroidViewModel(application)
{
    var usuarioRepository : UsuarioRepository
    lateinit var mUsuarioList : LiveData<List<UsuarioEntity>>
    init {
        usuarioRepository = UsuarioRepositoryImpl(application)
        suscribeToDbChanges()
    }


    fun suscribeToDbChanges()
    {
        mUsuarioList = usuarioRepository.obtenerUsuarios()
    }

    fun getUsuarios() : LiveData<List<UsuarioEntity>>
    {
        return mUsuarioList
    }

    fun getUsuario(user_id : Int ) : LiveData<UsuarioEntity>
    {
        return usuarioRepository.obtenerUsuario(user_id)
    }
}