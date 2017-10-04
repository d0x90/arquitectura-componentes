package com.pucp.diegocampos.firebasepruebas.request


import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.pucp.diegocampos.firebasepruebas.application.VolleyApplication
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by diegocamposaquino on 1/10/17.
 */
class ServiceVolley : ServiceInterface {
    val TAG = ServiceVolley::class.java.simpleName
    val BASE_PATH = "https://jsonplaceholder.typicode.com/"
    //val BASE_PATH="http://192.168.51.171/apiandroid/public/"


    override fun getJSONArray(path: String, completionHandler: (response: JSONArray?) -> Unit) {
        val jsonObjReq = object : JsonArrayRequest(Request.Method.GET, BASE_PATH + path, JSONArray(),
                Response.Listener<JSONArray> { response ->
                    Log.d(TAG, "/get request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/get request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }

        VolleyApplication.instance?.addToRequestQueue(jsonObjReq, TAG)
    }

    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Request.Method.GET, BASE_PATH + path, JSONObject(),
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/get request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/get request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }

        VolleyApplication.instance?.addToRequestQueue(jsonObjReq, TAG)
    }


    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.POST, BASE_PATH + path, null,
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/post request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }

        VolleyApplication.instance?.addToRequestQueue(jsonObjReq, TAG)
    }
}
