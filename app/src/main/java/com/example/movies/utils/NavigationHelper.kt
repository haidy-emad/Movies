package com.example.movies.utils

import android.app.Activity
import android.content.Intent
import com.example.movies.ui.details.MovieDetailsActivity

object NavigationHelper {
    fun goToMovieDetails(activity: Activity, id: Int) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(Constants.movieId, id)
        activity.startActivity(intent)
    }
}