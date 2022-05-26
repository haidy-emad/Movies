package com.example.movies.utils

import com.example.movies.data.model.Movie

sealed class MoviesUiState {
    class Success(val data: List<Movie>) : MoviesUiState()
    class Error(val exception: Exception) : MoviesUiState()
}