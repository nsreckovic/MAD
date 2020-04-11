package com.ns.mad_h1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity(R.layout.activity_article) {

    companion object {
        const val ADDED_TO_FAVORITES_KEY = "addToFavoritesKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val editor = getSharedPreferences(packageName, Context.MODE_PRIVATE).edit()

        val loggedInUser = sharedPreferences.getString(MainActivity.LOGGED_IN_USERNAME, "")
        if (loggedInUser != "") welcomeTv.setText("Welcome $loggedInUser, here's an article of the day, do you like it?")
        articleTv.setText(R.string.article)

        var addedToFavorites = sharedPreferences.getBoolean(ADDED_TO_FAVORITES_KEY, false)
        if (addedToFavorites) {
            starIv.setImageResource(R.drawable.ic_star_on_24dp)
            favoriteBtn.setText("REMOVE")
        } else {
            starIv.setImageResource(R.drawable.ic_star_off_24dp)
            favoriteBtn.setText("FAVORITE")
        }


        favoriteBtn.setOnClickListener {
            if (addedToFavorites) {
                addedToFavorites = false
                editor.putBoolean(ADDED_TO_FAVORITES_KEY, false)
                editor.commit()
                starIv.setImageResource(R.drawable.ic_star_off_24dp)
                favoriteBtn.setText("FAVORITE")
            } else {
                addedToFavorites = true
                editor.putBoolean(ADDED_TO_FAVORITES_KEY, true)
                editor.commit()
                starIv.setImageResource(R.drawable.ic_star_on_24dp)
                favoriteBtn.setText("REMOVE")
            }


        }

        logoutBtn.setOnClickListener {
            editor.putBoolean(MainActivity.LOGGED_IN_KEY, false)
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
