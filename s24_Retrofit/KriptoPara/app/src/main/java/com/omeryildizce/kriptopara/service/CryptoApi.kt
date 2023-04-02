package com.omeryildizce.kriptopara.service

import com.omeryildizce.kriptopara.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoApi {
    // GET, POST, UPDATE, DELETE
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Call<List<CryptoModel>>

}