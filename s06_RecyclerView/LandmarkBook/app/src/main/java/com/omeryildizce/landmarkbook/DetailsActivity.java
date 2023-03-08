package com.omeryildizce.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.omeryildizce.landmarkbook.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Viewbinding
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.countryText.setText("Test");
    }
}