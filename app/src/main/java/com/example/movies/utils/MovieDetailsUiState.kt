package com.example.movies.utils

import com.example.movies.data.model.MovieDetails

sealed class MovieDetailsUiState {
    class Success(val data: MovieDetails) : MovieDetailsUiState()
    class Error(val exception: Exception) : MovieDetailsUiState()
}