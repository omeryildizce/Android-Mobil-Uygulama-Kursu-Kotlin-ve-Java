package com.omeryildizce.retrofitcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omeryildizce.retrofitcompose.model.CryptoModel
import com.omeryildizce.retrofitcompose.service.CryptoApi
import com.omeryildizce.retrofitcompose.ui.theme.RetrofitComposeTheme
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val cryptoModels = remember {
        mutableStateListOf<CryptoModel>()
    }

    val BASE_URL = "https://raw.githubusercontent.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoApi::class.java)
    val call = retrofit.getData()
    call.enqueue(object : Callback<List<CryptoModel>> {
        override fun onResponse(
            call: Call<List<CryptoModel>>,
            response: Response<List<CryptoModel>>,
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    cryptoModels.addAll(it)
                }

            }
        }

        override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
            t.printStackTrace()
        }
    })

    Scaffold(topBar = { AppBar() }) {
        it.calculateBottomPadding()
        CryptoList(cryptos = cryptoModels)

    }
}

@Composable
fun AppBar() {
    TopAppBar(contentPadding = PaddingValues(10.dp)) {
        Text(text = "Retrofit Compose", fontSize = 26.sp, color = Color.White)
    }
}

@Composable
fun CryptoList(cryptos: List<CryptoModel>) {
    LazyColumn {
        items(cryptos) { crypto ->
            CryptoRow(crypto = crypto)
        }
    }
}

@Composable
fun CryptoRow(crypto: CryptoModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(randomColor()),

        ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                Text(text = crypto.currency, fontSize = MaterialTheme.typography.h4.fontSize)
                Text(
                    text = "$ ${crypto.price}",
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}


@Composable
fun randomColor(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetrofitComposeTheme {
        // MainScreen()
        CryptoRow(crypto = CryptoModel("BTC", "10000"))
    }
}