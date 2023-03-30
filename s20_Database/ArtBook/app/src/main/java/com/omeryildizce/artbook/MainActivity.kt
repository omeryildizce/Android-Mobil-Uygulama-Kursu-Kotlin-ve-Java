package com.omeryildizce.artbook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.omeryildizce.artbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var artList : ArrayList<Art>
    private lateinit var artAdapter:ArtAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        artList = ArrayList()
        artAdapter = ArtAdapter(artList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this,)
        binding.recyclerView.adapter = artAdapter
        readDatabase()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readDatabase() {
        try {
            val database = this@MainActivity.openOrCreateDatabase("Arts", MODE_PRIVATE, null)
            val cursor = database.rawQuery("select * from arts", null)
            val idIndex = cursor.getColumnIndex("id")
            val artNameIndex = cursor.getColumnIndex("artname")
            val artistNameIndex = cursor.getColumnIndex("artistname")
            val artYearIndex = cursor.getColumnIndex("year")
            val image = cursor.getColumnIndex("image")

            while (cursor.moveToNext()){
                val id = cursor.getInt(idIndex)
                val artName = cursor.getString(artNameIndex)
                val artistName = cursor.getString(artistNameIndex)
                val artYear = cursor.getString(artYearIndex)
                val artImage = cursor.getBlob(image)
                val art = Art(id,artName, artistName,artYear,artImage)
                artList.add(art)
            }
            artAdapter.notifyDataSetChanged()
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.art_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (R.id.add_art_item == item.itemId) {
            val intent = Intent(this, ArtActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}