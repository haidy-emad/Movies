package com.example.movies.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getMovies() = apiService.getMovies()

    suspend fun getMovieDetails(id: Int) = apiService.getMovieDetails(id)

}