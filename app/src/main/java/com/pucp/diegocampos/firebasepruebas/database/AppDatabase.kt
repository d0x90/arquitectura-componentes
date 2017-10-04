package com.pucp.diegocampos.firebasepruebas.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.pucp.diegocampos.firebasepruebas.database.dao.UsuarioDao
import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity

/**
 * Created by desarrollo on 04/10/2017.
 */

@Database(entities =  arrayOf(UsuarioEntity::class), version   = 1 )
abstract class AppDatabase : RoomDatabase()
{

    abstract fun usuarioDao() : UsuarioDao

    companion object {
        var DATABASE_NAME = "arquitectura_componentes"
        lateinit var INSTANCE : AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                //TODO Step 3: Prepare database instance
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME).build()
            }
            return INSTANCE
        }
    }
}