package com.example.movies.data.api

import com.example.movies.data.model.MoviesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(): MoviesResponse
}