package com.omeryildizce.instagramclone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.omeryildizce.instagramclone.databinding.ActivityUploadBinding
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var selectedPicture: Uri? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()



        registerLauncher()
        selectImage()
        binding.btnUploadImage.setOnClickListener { uploadPost() }

    }

    private fun initFirebase() {
        auth = Firebase.auth
        fireStore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
    }

    private fun uploadPost() {
        binding.btnUploadImage.isEnabled = false
        val uuid = UUID.randomUUID()
        val imageName = "$uuid.png"
        val reference = storage.reference
        val imageReference = reference.child("instagram/images").child(imageName)
        if (selectedPicture != null) {
            imageReference.putFile(selectedPicture!!).addOnSuccessListener {
                // download url - firestore
                val uploadImageReference =
                    storage.reference.child("instagram/images").child(imageName)
                uploadImageReference.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()
                    if (auth.currentUser != null) {
                        val postMap = hashMapOf<String, Any>()
                        postMap.put("downloadUrl", downloadUrl)
                        postMap.put("userEmail", auth.currentUser!!.email!!)
                        postMap.put("comment", binding.etPhotoDescription.text.toString())
                        postMap.put("date", Timestamp.now())

                        fireStore.collection("Posts").add(postMap).addOnSuccessListener {
                            finish()
                        }.addOnFailureListener {
                            Toast.makeText(this@UploadActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            }.addOnFailureListener {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                binding.btnUploadImage.isEnabled = true

            }
        }
    }

    private fun selectImage() {
        binding.imgPhotoPreview.setOnClickListener { view ->
            val readMediaImagesPermission =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }
            if (ContextCompat.checkSelfPermission(
                    this,
                    readMediaImagesPermission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        readMediaImagesPermission
                    )
                ) {
                    Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Give Permission")
                        {
                            // request permission
                            permissionLauncher.launch(readMediaImagesPermission)
                        }.show()
                } else {
                    // request permission
                    permissionLauncher.launch(readMediaImagesPermission)
                }
            } else {
                // start activity for result
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
                    val intentFromResult = result.data
                    if (intentFromResult != null) {
                        selectedPicture = intentFromResult.data
                        selectedPicture?.let {
                            binding.imgPhotoPreview.setImageURI(it)
                        }
                    }
                }
            }

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                // permission granted
                if (result) {
                    val intentToGallery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentToGallery)
                } else {
                    Toast.makeText(this, "Permission needed!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}