package com.gamecodeschool.photogallery

import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private var imagePaths: ArrayList<String>? = null
    private var imagesRV: RecyclerView? = null
    private var imageRVAdapter: RecyclerViewAdapter? = null

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imagePaths = ArrayList()
        imagesRV = findViewById<RecyclerView>(R.id.RVImages)


        prepareRecyclerView()

        requestPermissions()

    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun checkPermission(): Boolean {

       return Environment.isExternalStorageManager();
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun requestPermissions() {
        if (checkPermission()) {

            Toast.makeText(this, "Permissions granted..", Toast.LENGTH_SHORT).show()
            imagePath()
        } else {

            requestPermission()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun requestPermission() {

        try {
            val intent = Intent()
            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            val uri = Uri.fromParts("com.gamecodeschool.photogallery", this.packageName, null)
            intent.setData(uri)
            startActivity(intent)
        } catch (e: Exception) {
            val intent = Intent()
            intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            startActivity(intent)
        }
    }

    private fun prepareRecyclerView() {


        imageRVAdapter = RecyclerViewAdapter(this@MainActivity, ArrayList<String>())



        val manager = GridLayoutManager(this@MainActivity, 4)



        imagesRV!!.layoutManager = manager
        imagesRV!!.adapter = imageRVAdapter
    }

    private fun imagePath() {

        val isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

        if (isSDPresent) {

            val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)


            val orderBy = MediaStore.Images.Media._ID


            val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                null,
                null,
                orderBy
            )


            val count = cursor!!.count


            for (i in 0 until count) {

                cursor.moveToPosition(i)


                val dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)


                imagePaths!!.add(cursor.getString(dataColumnIndex))
            }
            imageRVAdapter!!.imagePathArrayList = imagePaths!!
            imageRVAdapter!!.notifyDataSetChanged()
            cursor.close()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE ->
                if (grantResults.size > 0) {
                    val storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (storageAccepted) {

                        Toast.makeText(this, "Permissions Granted..", Toast.LENGTH_SHORT).show()
                        imagePath()
                    } else {

                        Toast.makeText(
                            this,
                            "Permissions denied, Permissions are required to use the app..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    companion object {

        private const val PERMISSION_REQUEST_CODE = 200
    }
}