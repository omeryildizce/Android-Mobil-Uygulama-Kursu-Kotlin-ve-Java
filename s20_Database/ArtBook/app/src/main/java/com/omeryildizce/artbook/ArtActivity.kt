package com.omeryildizce.artbook

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.omeryildizce.artbook.databinding.ActivityArtBinding

class ArtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var selectedBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        registerLauncher()
        permission()

    }


    private fun permission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            binding.artImageView.setOnClickListener { view ->
                if (ContextCompat.checkSelfPermission(
                        this@ArtActivity,
                        android.Manifest.permission.READ_MEDIA_IMAGES
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this@ArtActivity,
                            android.Manifest.permission.READ_MEDIA_IMAGES
                        )
                    ) {
                        // Rationale
                        Snackbar.make(
                            view,
                            "Permission needed for gallery",
                            Snackbar.LENGTH_INDEFINITE
                        )
                            .setAction("Give Permisson", View.OnClickListener {
                                // request permission
                                permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                            }).show()
                    } else {
                        //request permission
                        permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                    }
                } else {
                    // intent
                    val intentToGalery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentToGalery)
                }
            }
        } else {
            binding.artImageView.setOnClickListener { view ->
                if (ContextCompat.checkSelfPermission(
                        this@ArtActivity,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this@ArtActivity,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    ) {
                        // Rationale
                        Snackbar.make(
                            view,
                            "Permission needed for gallery",
                            Snackbar.LENGTH_INDEFINITE
                        )
                            .setAction("Give Permisson", View.OnClickListener {
                                // request permission
                                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            }).show()
                    } else {
                        //request permission
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                } else {
                    // intent
                    val intentToGalery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentToGalery)
                }
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