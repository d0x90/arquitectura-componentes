package com.pucp.diegocampos.firebasepruebas.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by desarrollo on 04/10/2017.
 */

@Entity
class UsuarioEntity {

    @PrimaryKey(autoGenerate = true)
    var user_id = 0
    var name = ""
    var username = ""
    var email  =""
    var phone = ""
    var image = ""


}