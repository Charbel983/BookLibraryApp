package com.example.booklibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booklibraryapp.R
import com.example.booklibraryapp.controllers.FavoriteBooksController

class FavoriteBooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_books)

        val controller = FavoriteBooksController(this)
        controller.start()
    }
}