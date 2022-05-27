package com.example.movies.data.api

import com.example.movies.data.model.MovieDetails
import com.example.movies.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(): MoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetails
}