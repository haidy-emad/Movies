package com.example.movies.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.api.ApiHelper
import com.example.movies.data.api.RetrofitBuilder
import com.example.movies.data.model.MovieDetails
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

        viewModel.getMovieDetails(movieID)
    }

    private fun renderMovieDetailsUiState(state: MovieDetailsUiState) {
        when (state) {
            is MovieDetailsUiState.Success -> {
                fillMovieDetails(state.data)
            }

            is MovieDetailsUiState.Error -> {
                Toast.makeText(this, getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }
    }

    // todo - show/hide loading

    private fun fillMovieDetails(movie: MovieDetails) {
        with(movie) {
            Glide.with(binding.ivImage.context)
                .load(Constants.imagesBaseUrl + backdrop_path)
                .into(binding.ivImage)
            binding.tvTitle.text = title
            binding.tvGenre.text = genres.joinToString { it.name }
            binding.tvRating.text = "$vote_average / 10"
            binding.tvYear.text = release_date.substringBefore("-")
            binding.tvCountry.text = production_countries.joinToString { it.name }
            binding.tvDuration.text = getDuration(runtime)
            binding.tvOverview.text = overview
        }
    }

    private fun getDuration(runtime: Int): String {
        val hours: Int = runtime / 60
        val minutes: Int = runtime % 60
        return getString(R.string.duration_value, hours, minutes)
    }
}