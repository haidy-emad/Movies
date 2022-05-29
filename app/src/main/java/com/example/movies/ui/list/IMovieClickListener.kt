package com.example.movies.ui.list

import com.example.movies.data.model.Movie

interface IMovieClickListener {
    fun onMovieClicked(movie: Movie)
}