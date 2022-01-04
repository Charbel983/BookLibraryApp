package com.example.booklibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booklibraryapp.R
import com.example.booklibraryapp.controllers.CreateBookController

class CreateBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        val controller = CreateBookController(this)
        controller.start()
    }
}