package com.example.mystore

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

class GalleryActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_STORAGE_PERMISSION = 1
        private const val REQUEST_CODE_SELECT_IMAGE = 2
    }

    private var imageSelected: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        imageSelected = findViewById(R.id.img)
        findViewById<View>(R.id.btn).setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@GalleryActivity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_STORAGE_PERMISSION
                )
            } else {
                selectImage()
            }
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                val selectedImageUri = data.data
                if (selectedImageUri != null) {
                    try {
                        val inputStream = contentResolver.openInputStream(selectedImageUri)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        imageSelected!!.setImageBitmap(bitmap)


                        val selectedImageFile = File(getPathFromUri(selectedImageUri))
                    } catch (e: Exception) {
                        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun getPathFromUri(contentUri: Uri): String? {
        val filePath: String?
        val cursor = contentResolver
            .query(contentUri, null, null, null, null)
        if (cursor == null) {
            filePath = contentUri.path
        } else {
            cursor.moveToFirst()
            val index = cursor.getColumnIndex("_data")
            filePath = cursor.getString(index)
            cursor.close()
        }
        return filePath
    }
}