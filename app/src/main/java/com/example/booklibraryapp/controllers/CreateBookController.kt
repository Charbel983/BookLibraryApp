package com.example.booklibraryapp.controllers

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.booklibraryapp.R
import com.example.booklibraryapp.activities.CreateBookActivity
import com.example.booklibraryapp.activities.MainActivity
import com.example.booklibraryapp.database.Book
import com.example.booklibraryapp.database.BooksDB
import java.io.IOException

class CreateBookController (private val activity: CreateBookActivity) {
    private val image : ImageView = activity.findViewById(R.id.createBookImage)
    private val isbn : EditText = activity.findViewById(R.id.isbnBookEditText)
    private val title : EditText = activity.findViewById(R.id.titleBookEditText)
    private val author : EditText = activity.findViewById(R.id.authorBookEditText)
    private val date : EditText = activity.findViewById(R.id.publishedDateBookEditText)
    private val price : EditText = activity.findViewById(R.id.priceBookEditText)
    private val createButton : Button = activity.findViewById(R.id.createButton)
    private val database = BooksDB.get(activity).getBookDAO()

    private var selectedImage : Bitmap? = null

    private val imageLoader = activity.registerForActivityResult(ActivityResultContracts.GetContent()) {
        selectedImage = MediaStore.Images.Media.getBitmap(activity.contentResolver, it)
        image.setImageBitmap(selectedImage)
    }

    fun start() {
        image.setOnClickListener {
            imageLoader.launch("image/*")
        }

        createButton.setOnClickListener {
            val isbnCorrect: Boolean
            val titleCorrect: Boolean
            val authorCorrect: Boolean
            val dateCorrect: Boolean
            val priceCorrect: Boolean

            when {
                isbn.text.isEmpty() -> {
                    isbn.error = activity.getString(R.string.emptyField)
                    isbnCorrect = false
                }
                database.bookAlreadyExists(Integer.parseInt(isbn.text.toString())) -> {
                    isbn.error = "Book Already Exists"
                    isbnCorrect = false
                }
                else -> {
                    isbnCorrect = true
                }
            }
            if (title.text.isEmpty()) {
                title.error = activity.getString(R.string.emptyField)
                titleCorrect = false
            } else {
                titleCorrect = true
            }
            if (author.text.isEmpty()) {
                author.error = activity.getString(R.string.emptyField)
                authorCorrect = false
            } else {
                authorCorrect = true
            }
            if (date.text.isEmpty()) {
                date.error = activity.getString(R.string.emptyField)
                dateCorrect = false
            } else {
                dateCorrect = true
            }
            if (price.text.isEmpty()) {
                price.error = activity.getString(R.string.emptyField)
                priceCorrect = false
            } else {
                priceCorrect = true
            }

            if (isbnCorrect && titleCorrect && authorCorrect && dateCorrect && priceCorrect) {
                if (selectedImage != null) {
                    savePhoto(isbn.text.toString(), selectedImage!!)
                }
                val dir = isbn.text.toString() + ".jpg"
                val book = Book(
                    Integer.parseInt(isbn.text.toString()),
                    dir,
                    title.text.toString(),
                    author.text.toString(),
                    date.text.toString(),
                    Integer.parseInt(price.text.toString())
                )
                database.addBook(book)
                Toast.makeText(activity, activity.getString(R.string.bookCreated), Toast.LENGTH_LONG).show()
                activity.startActivity(Intent(activity, MainActivity::class.java))
            } else {
                Toast.makeText(activity, activity.getString(R.string.somethingWentWrong), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
    private fun savePhoto(filename : String, bmp : Bitmap) : Boolean {
        return try {
            activity.openFileOutput("$filename.jpg", AppCompatActivity.MODE_PRIVATE).use { stream ->
                if(!bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream)){
                    throw IOException("Couldn't save bmp")
                }
            }
            true
        }catch(e : IOException){
            e.printStackTrace()
            false
        }
    }
}