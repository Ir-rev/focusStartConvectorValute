package ir.rev.focusstartconvectorvalute.rest

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// экземпляр репозитория для банка
class BankRepository {

    private val bankApi: BankApi

    init {
        val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        bankApi = retrofit.create(BankApi::class.java)
    }

    fun getCurrency(): Call<JsonObject> {
        return bankApi.getCurrency()
    }

    companion object {
        private const val TAG = "CheckResult"
    }

}