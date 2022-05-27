package com.example.movies.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R
import com.example.movies.data.api.ApiHelper
import com.example.movies.data.api.RetrofitBuilder
import com.example.movies.databinding.ActivityMovieDetailsBinding
import com.example.movies.utils.Constants
import com.example.movies.utils.MovieDetailsUiState

class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel by viewModels<MovieDetailsViewModel> { MovieDetailsViewModelFactory(
        ApiHelper(
            RetrofitBuilder.apiService)
    ) }

    private lateinit var binding: ActivityMovieDetailsBinding
    private val movieID: Int by lazy {
        intent.getIntExtra(Constants.movieId, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getUiState().observe(this) {
            renderMovieDetailsUiState(it)
        }

        // todo - show movie details
        Toast.makeText(this, "movie id$movieID", Toast.LENGTH_SHORT).show()
        viewModel.getMovieDetails(movieID)
    }

    private fun renderMovieDetailsUiState(state: MovieDetailsUiState) {
        when (state) {
            is MovieDetailsUiState.Success -> {
                // TODO - FILL VIEW
                Toast.makeText(this, "success"+state.data, Toast.LENGTH_SHORT).show()
            }

            is MovieDetailsUiState.Error -> {
                Toast.makeText(this, getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }
    }
}