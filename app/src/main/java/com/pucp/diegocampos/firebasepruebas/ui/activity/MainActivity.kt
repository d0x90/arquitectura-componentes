package com.pucp.diegocampos.firebasepruebas.ui.activity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.pucp.diegocampos.firebasepruebas.R
import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity
import com.pucp.diegocampos.firebasepruebas.request.ApiController
import com.pucp.diegocampos.firebasepruebas.request.ServiceVolley
import com.pucp.diegocampos.firebasepruebas.ui.adapter.UsuarioAdapter
import com.pucp.diegocampos.firebasepruebas.ui.adapter.UsuarioClickCallback
import com.pucp.diegocampos.firebasepruebas.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner, UsuarioClickCallback {

    override fun onItemClick(usuario: UsuarioEntity) {

    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifeCycleRegister
    }

    var TAG = MainActivity::class.java.simpleName
    var usuarios = mutableListOf<UsuarioEntity>()
    var lifeCycleRegister = LifecycleRegistry(this)
    lateinit var usuarioViewModel : UsuarioViewModel
    lateinit var mUsuarioAdapter : UsuarioAdapter
    lateinit var mRecyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupList()
        initViewModel()
        suscribeUI()


    }
    fun obtenerServicio()
    {
        var service = ServiceVolley()
        var api = ApiController(service)
        //Obtener data del servicio
        api.getJSONArray("users",{ response ->
            if (response != null)
            {

                for ( item in 0 until response.length())
                {
                    var obj = response.get(item) as JSONObject
                    var usuario = UsuarioEntity()
                    usuario.user_id = obj.getInt("user_id")
                    usuario.name = obj.getString("name")
                    usuario.username = obj.getString("username")
                    usuario.email = obj.getString("email")
                    usuario.phone = obj.getString("phone")
                    usuario.image = obj.getString("image")
                    usuarios.add(usuario)
                }
            }
        })
    }
    fun setupList()
    {
        mUsuarioAdapter = UsuarioAdapter(this,this)
        mRecyclerView = recyclerView
        mRecyclerView.adapter = mUsuarioAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


    }

    fun initViewModel()
    {
        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel::class.java)
    }

    fun suscribeUI()
    {
        usuarioViewModel.getUsuarios().observe(this, Observer<List<UsuarioEntity>>{
            usuarioEntities ->
            if (usuarioEntities != null) {
                mUsuarioAdapter.swap(usuarioEntities )
            }
        })
    }
}
