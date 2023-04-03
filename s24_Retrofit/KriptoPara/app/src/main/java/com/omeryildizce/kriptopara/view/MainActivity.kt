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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , CryptoAdapter.Listener{
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var binding: ActivityMainBinding
    private lateinit var cryptoModels: ArrayList<CryptoModel>
    private lateinit var cryptoAdapter : CryptoAdapter
    private lateinit var compositeDisposable : CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        compositeDisposable = CompositeDisposable()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadData()



    }

    private fun loadData() {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CryptoApi::class.java)

        compositeDisposable.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleREsponse))



        // val service = retrofit.create(CryptoApi::class.java)
        // val call = service.getData()
        // retrofitCall(call)


    }
    private fun handleREsponse(cryptoList: List<CryptoModel>){
        cryptoModels = ArrayList(cryptoList)
        cryptoModels.let {
            cryptoAdapter = CryptoAdapter(it, this@MainActivity)
            binding.recyclerView.adapter = cryptoAdapter
        }
    }

    /*
    private fun retrofitCall(call: Observable<List<CryptoModel>>) {
        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { cryptoModelList ->
                        cryptoModels = ArrayList(cryptoModelList)
                        cryptoAdapter = CryptoAdapter(cryptoModels, this@MainActivity)
                        binding.recyclerView.adapter = cryptoAdapter
                    }
                }

            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
    */
    override fun onItemCick(cryptoModel: CryptoModel) {

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}