package com.pucp.diegocampos.firebasepruebas.ui.activity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.pucp.diegocampos.firebasepruebas.R
import com.pucp.diegocampos.firebasepruebas.database.entity.UsuarioEntity
import com.pucp.diegocampos.firebasepruebas.request.ApiController
import com.pucp.diegocampos.firebasepruebas.request.ServiceVolley
import com.pucp.diegocampos.firebasepruebas.ui.adapter.EndlessRecyclerViewScrollListener
import com.pucp.diegocampos.firebasepruebas.ui.adapter.UsuarioAdapter
import com.pucp.diegocampos.firebasepruebas.ui.adapter.UsuarioClickCallback
import com.pucp.diegocampos.firebasepruebas.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner, UsuarioClickCallback {

    override fun onItemClick(usuario: UsuarioEntity) {
        Toast.makeText(this,"Mostrando a "+usuario.name,Toast.LENGTH_LONG).show()
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
    var preferencias = getSharedPreferences("MyPrefs",Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupList()
        //initViewModel()
        //suscribeUI()
        obtenerData()

    }


    private fun obtenerData()
    {
        var ultSincronizacion = preferencias.getLong("ultima_sincronizacion",Long.MIN_VALUE)

        if ( obtenerDiferenciaTiempo(ultSincronizacion,1))
        {
            //obtener data del servicio
            llenarListaUsuarios(true)
            cambiarEstadoSincronizacion()
        }else
        {
            //obtener data de la base de datos local
            llenarListaUsuarios(false)
        }

    }

    private fun llenarListaUsuarios(flag : Boolean )
    {
        if (flag )
        {

            var service = ServiceVolley()
            var api = ApiController(service)
            //Obtener data del servicio
            api.getJSONArray("users",{ response ->
                if (response != null)
                {
                    usuarios.clear()
                    Log.d(TAG,"Usuarios obtenidos del servicio...")
                    for ( item in 0 until response.length())
                    {
                        var obj = response.get(item) as JSONObject
                        var usuario = UsuarioEntity()
                        usuario.user_id = obj.getInt("user_id")
                        usuario.name = obj.getString("name")
                        usuario.username = obj.getString("username")
                        usuario.email = obj.getString("email")
                        usuario.phone = obj.getString("phone")
                        usuario.image = obj.getString("website") // TODO: REEMPLAZAR POR URL IMAGEN
                        usuarios.add(usuario)
                    }
                    initViewModel()
                    suscribeUI()
                    mUsuarioAdapter.swap(usuarios)
                }
            })
        }else
        {
            /// YA SE TIENE LA DATA DE LA BD ??
            initViewModel()
            suscribeUI()
        }
    }

    private fun cambiarEstadoSincronizacion()
    {
        var editor = preferencias.edit()
        editor.putLong("ultima_sincronizacion",System.currentTimeMillis())
        editor.commit()
    }

    private fun obtenerDiferenciaTiempo(ultima_sincronizacion : Long, tiempo : Long) : Boolean
    {
        return System.currentTimeMillis() - ultima_sincronizacion >= TimeUnit.MINUTES.toMillis(tiempo)
    }


    private fun setupList()
    {
        mUsuarioAdapter = UsuarioAdapter(this,this)
        mRecyclerView = recyclerView
        mRecyclerView.adapter = mUsuarioAdapter
        var layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mRecyclerView.layoutManager = layoutManager
        manejarScroll(layoutManager)

    }

    private fun initViewModel()
    {
        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel::class.java)
    }

    private fun suscribeUI()
    {
        usuarioViewModel.getUsuarios().observe(this, Observer<List<UsuarioEntity>>{
            usuarioEntities ->
            if (usuarioEntities != null) {
                mUsuarioAdapter.swap(usuarioEntities )
            }
        })
    }

    private fun manejarScroll(linearLayoutManager : LinearLayoutManager)
    {
        var endless = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {

            }
        }
        mRecyclerView.addOnScrollListener(endless)
    }
}
