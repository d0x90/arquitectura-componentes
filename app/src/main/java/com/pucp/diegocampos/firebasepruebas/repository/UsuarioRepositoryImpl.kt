package com.pucp.diegocampos.firebasepruebas.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.pucp.diegocampos.firebasepruebas.database.AppDatabase
import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity

/**
 * Created by desarrollo on 04/10/2017.
 */
class UsuarioRepositoryImpl(application : Application)  : UsuarioRepository
{
    var mDb  = AppDatabase.getDatabase(application)

    override fun obtenerUsuarios(): LiveData<List<UsuarioEntity>> {
        return mDb.usuarioDao().loadAllUsers()
    }

    override fun obtenerUsuario(user_id: Int): LiveData<UsuarioEntity> {
        return mDb.usuarioDao().loadUser(user_id)
    }

    override fun insertarUsuario(usuario: UsuarioEntity) {
        return mDb.usuarioDao().insertUser(usuario)
    }

    override fun editarUsuario(usuario: UsuarioEntity) {
        return mDb.usuarioDao().updateUser(usuario)
    }

    override fun eliminarUsuario(user_id: Int) {
        return mDb.usuarioDao().deleteUser(user_id)
    }

}