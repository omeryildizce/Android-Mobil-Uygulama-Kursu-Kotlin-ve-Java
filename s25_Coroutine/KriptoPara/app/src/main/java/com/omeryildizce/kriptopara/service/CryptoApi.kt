package com.omeryildizce.kriptopara.service

import com.omeryildizce.kriptopara.model.CryptoModel
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CryptoApi {
    // GET, POST, UPDATE, DELETE
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    //fun getData() : Observable<List<CryptoModel>>
    // fun getData(): Call<List<CryptoModel>>
     suspend fun getData(): Response<List<CryptoModel>>

}