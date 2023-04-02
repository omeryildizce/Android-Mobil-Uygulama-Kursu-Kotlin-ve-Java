package com.omeryildizce.kriptopara.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omeryildizce.kriptopara.R
import com.omeryildizce.kriptopara.adapter.CryptoAdapter
import com.omeryildizce.kriptopara.databinding.ActivityMainBinding
import com.omeryildizce.kriptopara.model.CryptoModel
import com.omeryildizce.kriptopara.service.CryptoApi
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , CryptoAdapter.Listener{
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var binding: ActivityMainBinding
    private lateinit var cryptoModels: ArrayList<CryptoModel>
    private lateinit var cryptoAdapter : CryptoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadData()



    }

    private fun loadData() {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoApi::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>,
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModelList ->
                        cryptoModels = ArrayList(cryptoModelList)
                        cryptoAdapter = CryptoAdapter(cryptoModels,this@MainActivity )
                        binding.recyclerView.adapter = cryptoAdapter
                    }
                }
                
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemCick(cryptoModel: CryptoModel) {

    }
}