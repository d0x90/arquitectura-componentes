package com.pucp.diegocampos.firebasepruebas.request


import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by diegocamposaquino on 1/10/17.
 */
class ApiController (serviceInterface: ServiceInterface) : ServiceInterface{
    var serviceInterface = serviceInterface

    override fun getJSONArray(path: String, completionHandler: (response: JSONArray?) -> Unit) {
        serviceInterface.getJSONArray(path,completionHandler)
    }



    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        serviceInterface.post(path,params,completionHandler)
    }



    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
        serviceInterface.get(path,completionHandler)

    }
}