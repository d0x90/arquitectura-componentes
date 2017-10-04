package com.pucp.diegocampos.firebasepruebas.application


import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by diegocamposaquino on 1/10/17.
 */
open class VolleyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
        //MultiDex.install(this)

    }

    val requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    fun <T> addToRequestQueue(request: Request<T>, tag: String) {
        request.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue?.add(request)
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        request.tag = TAG
        requestQueue?.add(request)
    }

    fun cancelPendingRequests(tag: Any) {
        if (requestQueue != null) {
            requestQueue!!.cancelAll(tag)
        }
    }

    companion object {
        private val TAG = VolleyApplication::class.java.simpleName
        @get:Synchronized var instance: VolleyApplication? = null
            private set
    }
}