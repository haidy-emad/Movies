package com.example.movies

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.data.api.ApiHelper
import com.example.movies.data.api.RetrofitBuilder
import com.example.movies.utils.MoviesUiState

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> { MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getUiState().observe(this) {
            renderMoviesUiState(it)
        }
    }

    private fun renderMoviesUiState(state: MoviesUiState) {
        when (state) {
            is MoviesUiState.Success -> {
                // todo - show on screen
                Toast.makeText(this, state.data.toString(), Toast.LENGTH_SHORT).show()
            }

            is MoviesUiState.Error -> {
                Toast.makeText(this, getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }
    }
}