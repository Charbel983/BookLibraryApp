package com.example.booklibraryapp.controllers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booklibraryapp.R
import com.example.booklibraryapp.activities.FavoriteBooksActivity
import com.example.booklibraryapp.adapters.FavoriteBooksAdapter
import com.example.booklibraryapp.database.favorites.FavoriteBook
import com.example.booklibraryapp.database.favorites.FavoriteBooksDB

class FavoriteBooksController(private val activity : FavoriteBooksActivity) {
    private lateinit var recyclerView: RecyclerView

    fun start() {
        recyclerView = activity.findViewById(R.id.favoritesRecyclerView)
        val database = FavoriteBooksDB.get(activity).getFavoritesDAO()
        val favorites = database.getAllFavorites()
        showData(favorites)
    }
    private fun showData(favorites : List<FavoriteBook>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = FavoriteBooksAdapter(favorites, this.context)
        }
    }
}