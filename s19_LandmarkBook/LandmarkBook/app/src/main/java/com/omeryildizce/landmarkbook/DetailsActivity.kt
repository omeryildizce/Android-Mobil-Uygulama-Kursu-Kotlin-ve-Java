package com.omeryildizce.landmarkbook

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omeryildizce.landmarkbook.databinding.ActivityDetailsBinding
import java.io.Serializable

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent

        val selectedLandmark = getSerializable(this, "landmark", Landmark::class.java)
        binding.countryText.text = selectedLandmark.country
        binding.nameText.text = selectedLandmark.name
        binding.imageViewLandmark.setImageResource(selectedLandmark.image)
    }

    fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T
    {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
    }
}