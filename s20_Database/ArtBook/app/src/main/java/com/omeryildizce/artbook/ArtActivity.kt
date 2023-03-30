package com.omeryildizce.artbook

import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.omeryildizce.artbook.databinding.ActivityArtBinding
import java.io.ByteArrayOutputStream

class ArtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var selectedBitmap: Bitmap? = null
    private lateinit var database:SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        database = this.openOrCreateDatabase("Arts", MODE_PRIVATE, null)

        registerLauncher()
        permission()

        val info = intent.getStringExtra("info")
        if (info.equals("new")){
            binding.artNameEditText.setText("")
            binding.artistNameEditText.setText("")
            binding.artYearEditText.setText("")
            binding.artImageView.setImageResource(R.drawable.image)
            binding.saveArtButton.visibility = View.VISIBLE
            binding.deleteArtButton.visibility = View.GONE
        }else{
            val selectedId = intent.getIntExtra("id",1)

            binding.deleteArtButton.visibility = View.GONE
            binding.saveArtButton.visibility = View.GONE
            val cursor = database.rawQuery("select * from arts where id = ?", arrayOf(selectedId.toString()))
            val artNameIndex = cursor.getColumnIndex("artname")
            val artistNameIndex = cursor.getColumnIndex("artistname")
            val artYearIndex = cursor.getColumnIndex("year")
            val artImageIndex = cursor.getColumnIndex("image")

            while (cursor.moveToNext()){
                binding.artNameEditText.setText(cursor.getString(artNameIndex))
                binding.artistNameEditText.setText(cursor.getString(artistNameIndex))
                binding.artYearEditText.setText(cursor.getString(artYearIndex))
                val imageByteArray = cursor.getBlob(artImageIndex)
                val bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
                binding.artImageView.setImageBitmap(bitmap)
            }
            cursor.close()

        }
        save()

    }

    private fun save() {
        binding.saveArtButton.setOnClickListener {
            val artName = binding.artNameEditText.text.toString()
            val artistName = binding.artistNameEditText.text.toString()
            val artYear = binding.artYearEditText.text.toString()

            if (selectedBitmap != null) {
                val smalBitmap = makeSmallerBitmap(selectedBitmap!!, 300)
                val outputStream = ByteArrayOutputStream()
                smalBitmap.compress(Bitmap.CompressFormat.PNG, 70, outputStream)
                val byteArray = outputStream.toByteArray()

                try {
                    insertDatabase(artName, artistName, artYear, byteArray)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val intent = Intent(this@ArtActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)

            }
        }

    }

    private fun insertDatabase(
        artName: String,
        artistName: String,
        artYear: String,
        byteArray: ByteArray?,
    ) {
        database.execSQL("create table if not exists arts (id INTEGER primary key AUTOINCREMENT, artname varchar, artistname varchar, year varchar, image blob )")
        val sqlString = "insert into arts (artname , artistname , year , image  ) values (?,?,?,?)"
        val statement = database.compileStatement(sqlString)
        statement.bindString(1, artName)
        statement.bindString(2, artistName)
        statement.bindString(3, artYear)
        statement.bindBlob(4, byteArray)
        statement.execute()
    }

    private fun makeSmallerBitmap(image: Bitmap, maximumSize: Int): Bitmap {
        var width = image.width
        var height = image.height
        val bitmapRatio: Double = width / height.toDouble()
        if (bitmapRatio > 1) {
            width = maximumSize
            val scaledHeight = width / bitmapRatio
            height = scaledHeight.toInt()
        } else {
            height = maximumSize
            val scaledWidth = height * bitmapRatio
            width = scaledWidth.toInt()
        }


        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    private fun permission() {
        binding.artImageView.setOnClickListener { view ->
            val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                android.Manifest.permission.READ_MEDIA_IMAGES
            } else {
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            }

            if (ContextCompat.checkSelfPermission(
                    this@ArtActivity,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this@ArtActivity,
                        permission
                    )
                ) {
                    // Show rationale if needed
                    Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Give Permission") {
                            // Request permission
                            permissionLauncher.launch(permission)
                        }
                        .show()
                } else {
                    // Request permission
                    permissionLauncher.launch(permission)
                }
            } else {
                // Permission already granted, launch gallery intent
                val intentToGallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }


    }

    private fun registerLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val intentForResult = result.data
                    if (intentForResult != null) {
                        val imageData = intentForResult.data
                        // binding.artImageView.setImageURI(imageData)
                        if (imageData != null) {
                            try {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                    val source =
                                        ImageDecoder.createSource(
                                            this@ArtActivity.contentResolver,
                                            imageData
                                        )
                                    selectedBitmap = ImageDecoder.decodeBitmap(source)
                                    binding.artImageView.setImageBitmap(selectedBitmap)
                                } else {
                                    selectedBitmap = MediaStore.Images.Media.getBitmap(
                                        this@ArtActivity.contentResolver,
                                        imageData
                                    )
                                    binding.artImageView.setImageBitmap(selectedBitmap)
                                }

                            } catch (e: java.lang.Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }

            }
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    // permission granted
                    val intentToGallery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentToGallery)
                } else {
                    // permission denied
                    Toast.makeText(this@ArtActivity, "Permission needed!", Toast.LENGTH_LONG).show()
                }
            }
    }

}