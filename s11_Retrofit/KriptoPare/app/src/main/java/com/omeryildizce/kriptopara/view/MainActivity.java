package com.omeryildizce.kriptopara.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.omeryildizce.kriptopara.model.CryptoModel;
import com.omeryildizce.kriptopara.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        CryptoModel cryptoModel = new CryptoModel();

    }
}