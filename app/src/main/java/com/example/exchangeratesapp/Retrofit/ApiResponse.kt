package com.example.exchangeratesapp.Retrofit

import android.util.Log
import com.example.exchangeratesapp.model.JsonModel
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class ApiResponse {
    lateinit var data : JsonModel.Valute.myValGeneralValute
    val directory = "Valute"
/*
    fun getData() {
        CourseApi.invoke().searchMainData().enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                var str = response.body().toString()
                val jss = JSONObject(str)
                var keys = jss.getJSONObject(directory).names()

                for (i in 0 until keys.length()) {
                    data.charCode =
                        jss.getJSONObject(directory)
                            .getJSONObject(keys[i]!!.toString())
                            .get("CharCode").toString()


                    data.name =
                        jss.getJSONObject(directory)
                            .getJSONObject(keys[i]!!.toString())
                            .get("Name").toString()


                    data.value =
                        jss.getJSONObject(directory)
                            .getJSONObject(keys[i]!!.toString())
                            .get("Value").toString()


                    data.previous =
                        jss.getJSONObject(directory)
                            .getJSONObject(keys[i]!!.toString())
                            .get("Previous").toString()

                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }*/
}

