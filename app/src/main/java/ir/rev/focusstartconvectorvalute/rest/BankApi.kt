package ir.rev.focusstartconvectorvalute.rest


import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

// из гайда по ретрофиту, тут будут запросы
interface BankApi {

    @GET("daily_json.js")
    fun getCurrency(): Call<JsonObject>

}

