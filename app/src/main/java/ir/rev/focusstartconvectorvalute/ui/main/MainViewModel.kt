package ir.rev.focusstartconvectorvalute.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import ir.rev.focusstartconvectorvalute.response.Currency
import ir.rev.focusstartconvectorvalute.rest.BankRepository
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val TAG = "checkResult"

    private val repository = BankRepository()

    private val _currencyLiveData = MutableLiveData<List<Currency>>()
    val currencyLiveData: LiveData<List<Currency>> = _currencyLiveData

    // запрос в сеть для получения валюты
    fun getCurrency() {
        repository.getCurrency().enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val currentResponse = response.body()
                if (currentResponse != null) {
                    _currencyLiveData.value = currentResponse
                        .getAsJsonObject("Valute")
                        .entrySet()
                        .asIterable()
                        .map {
                            val value = it.value.asJsonObject
                            Currency(
                                id = value.get("ID").asString,
                                numCode = value.get("NumCode").asString,
                                charCode = value.get("CharCode").asString,
                                nominal = value.get("Nominal").asInt,
                                name = value.get("Name").asString,
                                value = value.get("Value").asDouble,
                                previous = value.get("Previous").asDouble
                            )
                        }
                } else {
                    Log.d(TAG, "ошибка загрузки ответ пустой")
                }
            }

            override fun onFailure(call: Call<JsonObject>, throwable: Throwable) {
                Log.d(TAG, "ошибка загрузки", throwable)
            }

        })
    }

}