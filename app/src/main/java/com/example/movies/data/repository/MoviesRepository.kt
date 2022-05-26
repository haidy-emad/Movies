package com.example.movies.data.repository

import com.example.movies.data.api.ApiHelper

class MoviesRepository (private val apiHelper: ApiHelper) {

    suspend fun getMovies() = apiHelper.getMovies()

}