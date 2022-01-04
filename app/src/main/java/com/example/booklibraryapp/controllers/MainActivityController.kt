package com.example.booklibraryapp.controllers

import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.booklibraryapp.R
import com.example.booklibraryapp.activities.*

class MainActivityController(private val activity : MainActivity) {

    fun start() {
        val allBooksButton : Button = activity.findViewById(R.id.allBooksButton)
        val createBookButton : Button = activity.findViewById(R.id.createBookButton)
        val cameraButton : Button = activity.findViewById(R.id.cameraButton)
        val favoritesButton : Button = activity.findViewById(R.id.favoritesButton)
        val aboutUsButton : Button = activity.findViewById(R.id.aboutUsButton)

        allBooksButton.setOnClickListener {
            activity.startActivity(Intent(activity, AllBooksActivity::class.java))
        }

        createBookButton.setOnClickListener {
            activity.startActivity(Intent(activity, CreateBookActivity::class.java))
        }

        cameraButton.setOnClickListener {
            if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.CAMERA), 1222)
            }else{
                activity.startActivity(Intent("android.media.action.IMAGE_CAPTURE"))
            }
        }

        favoritesButton.setOnClickListener {
            activity.startActivity(Intent(activity, FavoriteBooksActivity::class.java))
        }

        aboutUsButton.setOnClickListener {
            activity.startActivity(Intent(activity, AboutUsActivity::class.java))
        }
    }
}