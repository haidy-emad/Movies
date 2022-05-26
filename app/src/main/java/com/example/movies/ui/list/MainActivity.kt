package com.example.movies.ui.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R
import com.example.movies.data.api.ApiHelper
import com.example.movies.data.api.RetrofitBuilder
import com.example.movies.data.model.Movie
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.utils.MoviesUiState
import com.example.movies.utils.NavigationHelper

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> { MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService)) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getUiState().observe(this) {
            renderMoviesUiState(it)
        }
    }

    private fun renderMoviesUiState(state: MoviesUiState) {
        when (state) {
            is MoviesUiState.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.rvMovies.visibility = View.VISIBLE
                binding.rvMovies.adapter = MoviesAdapter(state.data as ArrayList<Movie>, object: IMovieClickListener {
                    override fun onMovieClicked(id: Int) {
                        NavigationHelper.goToMovieDetails(this@MainActivity, id)
                    }
                })
            }

            is MoviesUiState.Error -> {
                Toast.makeText(this, getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }
    }
}