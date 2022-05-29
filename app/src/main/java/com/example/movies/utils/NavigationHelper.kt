package com.example.movies.utils

import android.app.Activity
import android.content.Intent
import com.example.movies.data.model.Movie
import com.example.movies.ui.details.MovieDetailsActivity

object NavigationHelper {
    fun goToMovieDetails(activity: Activity, movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(Constants.movieId, movie.id)
        intent.putExtra(Constants.movieTitle, movie.title)
        activity.startActivity(intent)
    }
}