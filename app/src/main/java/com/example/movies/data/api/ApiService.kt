package com.example.movies.data.api

import com.example.movies.data.model.MoviesResponse
import retrofit2.http.GET

interface ApiService {

    // todo - api key
    @GET("discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getMovies(): MoviesResponse
}