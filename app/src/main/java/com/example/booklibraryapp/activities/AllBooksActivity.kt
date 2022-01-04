package com.example.booklibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booklibraryapp.R
import com.example.booklibraryapp.controllers.AllBooksController

class AllBooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)

        val controller = AllBooksController(this)
        controller.start()
    }
}