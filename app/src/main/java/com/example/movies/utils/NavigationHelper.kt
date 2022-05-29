package com.example.movies.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.example.movies.data.model.Movie
import com.example.movies.ui.details.MovieDetailsActivity


object NavigationHelper {
    fun goToMovieDetails(activity: Activity, movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(Constants.movieId, movie.id)
        intent.putExtra(Constants.movieTitle, movie.title)
        activity.startActivity(intent)
    }

    fun openMovieHomepage(activity: Activity, homepageUrl: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(homepageUrl)
        activity.startActivity(i)
    }

    fun openImdbPage(activity: Activity, imdbId: String) {
        val url = "https://www.imdb.com/title/$imdbId/"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        activity.startActivity(i)
    }
}