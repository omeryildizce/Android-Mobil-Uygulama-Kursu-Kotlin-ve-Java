package com.omeryildizce.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.omeryildizce.landmarkbook.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    Landmark pisa;
    Landmark eiffel;
    Landmark colloseum;
    Landmark londonbridge;
    ArrayList<Landmark> landmarks;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Data
        pisa = new Landmark("Pisa", "Italy", R.drawable.pisa);
        eiffel = new Landmark("Eiffel", "France", R.drawable.eiffel);
        colloseum = new Landmark("Colloseum", "Italy", R.drawable.colloseum);
        londonbridge = new Landmark("London Bridge", "England", R.drawable.londonbridge);

        landmarks = new ArrayList<Landmark>();
        landmarks.add(pisa);
        landmarks.add(eiffel);
        landmarks.add(colloseum);
        landmarks.add(londonbridge);

        // Adapter
        // Listview
        // mapping
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, landmarks.stream().map(landmark -> landmark.name).collect(Collectors.toList()));
        binding.listView.setAdapter(arrayAdapter);
    }
}