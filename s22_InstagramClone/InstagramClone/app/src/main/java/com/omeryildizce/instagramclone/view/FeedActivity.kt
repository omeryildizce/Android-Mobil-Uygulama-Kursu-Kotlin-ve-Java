package com.omeryildizce.instagramclone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.omeryildizce.instagramclone.adapter.FeedAdapter
import com.omeryildizce.instagramclone.databinding.ActivityFeedBinding
import com.omeryildizce.instagramclone.model.Post

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var database : FirebaseFirestore
    private lateinit var postArrayList: ArrayList<Post>
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postArrayList = ArrayList<Post>()

        initFirebase()
        setUpLogoutDialog()
        getData()

        binding.rvFeedPosts.layoutManager = LinearLayoutManager(this)
        feedAdapter = FeedAdapter(postArrayList)
        binding.rvFeedPosts.adapter = feedAdapter
        binding.uploadImage.setOnClickListener{ startUploadActivity()}


    }
    private fun getData(){
        database.collection("Posts").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error != null){
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }else{
                if (value != null){
                    if (!value.isEmpty){
                        val documents = value.documents
                        postArrayList.clear()
                        for (document in documents){
                            val comment = document.get("comment") as String
                            val userEmail = document.get("userEmail") as String
                            val downloadUrl = document.get("downloadUrl") as String
                            val displayName = auth.currentUser?.displayName.toString()

                            val post = Post(userEmail,comment,downloadUrl,displayName)
                            postArrayList.add(post)

                        }
                        feedAdapter.notifyDataSetChanged()
                    }
                }
            }

        }
    }

    private fun initFirebase() {
        auth = Firebase.auth
        database = FirebaseFirestore.getInstance()
    }

    private fun startUploadActivity() {
        val intent = Intent(this,  UploadActivity::class.java)
        startActivity(intent)
    }

    fun setUpLogoutDialog() {
        // Set up the alert dialog builder
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to log out?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                // Navigate to login page
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                auth.signOut()
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                // Do nothing and dismiss the dialog
                dialog.dismiss()
            }

        // Set up the app icon click listener
        binding.ivLogout.setOnClickListener {
            // Show the alert dialog
            builder.create().show()
        }
    }

}