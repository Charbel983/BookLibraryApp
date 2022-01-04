package com.example.booklibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booklibraryapp.R
import com.example.booklibraryapp.controllers.MainActivityController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controller = MainActivityController(this)
        controller.start()
    }
}