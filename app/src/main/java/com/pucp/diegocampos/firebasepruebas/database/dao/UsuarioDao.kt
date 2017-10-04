package com.pucp.diegocampos.firebasepruebas.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity

/**
 * Created by desarrollo on 04/10/2017.
 */
@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuarios")
     fun loadAllUsers()  : LiveData<List<UsuarioEntity>>

    @Query("SELECT * FROM usuarios WHERE user_id= :user_id")
    fun loadUser(user_id : Int) : LiveData<UsuarioEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user:  UsuarioEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: UsuarioEntity)

    @Query("DELETE FROM usuarios WHERE user_id= :user_id")
    fun deleteUser(user_id: Int)



}