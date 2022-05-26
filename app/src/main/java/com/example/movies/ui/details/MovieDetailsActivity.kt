package com.example.movies.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.databinding.ActivityMovieDetailsBinding
import com.example.movies.utils.Constants

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val movieID: Int by lazy {
        intent.getIntExtra(Constants.movieId, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // todo - show movie details
        Toast.makeText(this, "movie id$movieID", Toast.LENGTH_SHORT).show()
    }
}