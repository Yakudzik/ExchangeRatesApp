package com.example.exchangeratesapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangeratesapp.Retrofit.CourseApi
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.exchangeratesapp.model.JsonModel.Valute.myValGeneralValute
import java.util.*

class CurrencyViewModelClass() : ViewModel() {

    private var arrayGeneral: MutableList<myValGeneralValute> =
        Collections.synchronizedList(ArrayList<myValGeneralValute>())
    private var arrayConverter: MutableList<JsonModel.Valute.mySimpleValute> =
        Collections.synchronizedList(ArrayList<JsonModel.Valute.mySimpleValute>())

    private var _testCurrencyList = MutableLiveData<List<myValGeneralValute>>()
    private var _convertedCurrencyList = MutableLiveData<List<JsonModel.Valute.mySimpleValute>>()

    val testCurrencyListData: LiveData<List<myValGeneralValute>> = _testCurrencyList
    val convertedCurrencyList: LiveData<List<JsonModel.Valute.mySimpleValute>> =
        _convertedCurrencyList

    private val directoryConst = "Valute"

    private fun createObj(
        numCode: String,
        charCode: String,
        nominal: Int,
        name: String,
        value: String,
        previous: String
    ): myValGeneralValute {
        return myValGeneralValute(numCode, charCode, nominal, name, value, previous)
    }

    private fun createConverterObj(
        charCode: String,
        name: String,
        originalValue: String,
        newlValue: String
    ): JsonModel.Valute.mySimpleValute {
        return JsonModel.Valute.mySimpleValute(charCode, name, originalValue, newlValue)
    }

    fun testAwakeData() {
        CourseApi.invoke().searchMainData().enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful) {
                    val str = response.body().toString() //достаем данные
                    val jss =
                        JSONObject(str) //преобразуем данные в Json объект чтобы распарсить циклом
                    val keys =
                        jss.getJSONObject(directoryConst).names() // массив наименований валют
                    val kLength = keys!!.length() //число длины массива имен

                    for (i in 0 until kLength) {
                        arrayGeneral.add(
                            createObj(
                                "",
                                jss.getJSONObject(directoryConst)
                                    .getJSONObject(keys[i]!!.toString())
                                    .get("CharCode").toString(),
                                jss.getJSONObject(directoryConst)
                                    .getJSONObject(keys[i]!!.toString())
                                    .get("Nominal").toString().toInt(),
                                jss.getJSONObject(directoryConst)
                                    .getJSONObject(keys[i]!!.toString())
                                    .get("Name").toString(),

                                getOneCurrencyValueFromNominalDifference(
                                    jss.getJSONObject(directoryConst)
                                        .getJSONObject(keys[i]!!.toString())
                                        .get("Nominal").toString(),
                                    jss.getJSONObject(directoryConst)
                                        .getJSONObject(keys[i]!!.toString())
                                        .get("Value").toString()
                                ),
                                getCurrencyDifference(
                                    jss.getJSONObject(directoryConst)
                                        .getJSONObject(keys[i]!!.toString())
                                        .get("Value").toString(),
                                    jss.getJSONObject(directoryConst)
                                        .getJSONObject(keys[i]!!.toString())
                                        .get("Previous").toString()
                                )
                            )
                        )
                        arrayConverter.add(
                            createConverterObj(
                                jss.getJSONObject(directoryConst)
                                    .getJSONObject(keys[i]!!.toString())
                                    .get("CharCode").toString(),
                                jss.getJSONObject(directoryConst)
                                    .getJSONObject(keys[i]!!.toString())
                                    .get("Name").toString(),
                                oneRubleValue(
                                    getOneCurrencyValueFromNominalDifference(
                                        jss.getJSONObject(directoryConst)
                                            .getJSONObject(keys[i]!!.toString())
                                            .get("Nominal").toString(),
                                        jss.getJSONObject(directoryConst)
                                            .getJSONObject(keys[i]!!.toString())
                                            .get("Value").toString()
                                    )
                                ), ""
                            )
                        )
                    }
                    generateDataList() //возможно тут при обмене теряется последнее значение

                    generateCurrencyConvertedList()

                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e("Response error", "CurrencyViewModel class error")
            }
        })
    }

    private fun getCurrencyDifference(currentVal: String, previousVal: String): String {
        val c = currentVal.toDouble()
        val p = previousVal.toDouble()

        return if (c > p) {
            val res = ("+${c - p} ↑")
            (res.substring(0, 7) + " ▲")
        } else {
            val res = ("-${p - c}")
            (res.substring(0, 7) + " ▼")
        }
    }

    private fun getOneCurrencyValueFromNominalDifference(
        nominalVal: String,
        currentVal: String
    ): String {
        val nominal = nominalVal.toInt()
        val currency = currentVal.toDouble()
        return if (nominal != 1) {
            val res = (currency / nominal).toString()
            (res.substring(0, 6))
        } else {
            val res = currency.toString()
            (res.substring(0, 6))
        }
    }

    private fun oneRubleValue(value: String): String {
        val res = (1 / value.toDouble()).toString()
        return (res.substring(0, 6))
    }

    private fun generateCurrencyConvertedList() {
        _convertedCurrencyList.value =
            arrayConverter
    }

    private fun generateDataList() {
        _testCurrencyList.value =
            arrayGeneral
    }

    fun calculateCourse(double: Double) {
        //  var testArray: MutableList<JsonModel.Valute.mySimpleValute>

        for (i in 0.._convertedCurrencyList.value!!.lastIndex) {
            _convertedCurrencyList.value!![i].newlValue =
                (_convertedCurrencyList.value!![i].originalValue.toDouble() * double).toString()
        }
    }
}