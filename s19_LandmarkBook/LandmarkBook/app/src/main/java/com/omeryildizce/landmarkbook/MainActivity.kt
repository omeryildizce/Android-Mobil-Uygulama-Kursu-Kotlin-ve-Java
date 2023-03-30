package com.omeryildizce.landmarkbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.omeryildizce.landmarkbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var landmarkList: ArrayList<Landmark>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // Load Data
        loadLandmarkData()
        // listView()

        // recycler view
        recyclerView()
    }

    private fun recyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        val landmarkAdapter = LandmarkAdapter(landmarkList)
        binding.recyclerView.adapter = landmarkAdapter
    }

    /*
    private fun listView() {
        // Adapter : Layout : Data
        val adapter =
            ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_list_item_1,
                landmarkList.map { landmark ->
                    // Mapping
                    landmark.name
                })
        binding.listView.adapter = adapter
        binding.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("landmark", landmarkList.get(position))
                startActivity(intent)

            }
    }
    */

    private fun loadLandmarkData() {
        val pisa = Landmark("Pisa", "Italy", R.drawable.pisa)
        val colloseum = Landmark("Colloseum", "Italy", R.drawable.colloseum)
        val eiffel = Landmark("Eiffel", "France", R.drawable.eiffel)
        val londonbridge = Landmark("London Bridge", "England", R.drawable.londonbridge)
        
        landmarkList = ArrayList<Landmark>()
        landmarkList.add(pisa)
        landmarkList.add(eiffel)
        landmarkList.add(colloseum)
        landmarkList.add(londonbridge)
    }
}