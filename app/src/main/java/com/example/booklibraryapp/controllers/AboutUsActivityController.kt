package com.example.booklibraryapp.controllers

import android.widget.TextView
import com.example.booklibraryapp.R
import com.example.booklibraryapp.activities.AboutUsActivity

class AboutUsActivityController(private val activity : AboutUsActivity) {

    fun start() {
        val description : TextView = activity.findViewById(R.id.aboutUsDescription)

        description.text = activity.getString(R.string.description)
    }
}