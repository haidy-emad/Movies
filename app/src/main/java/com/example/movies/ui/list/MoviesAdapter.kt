package com.example.movies.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.data.model.Movie
import com.example.movies.databinding.ItemMovieBinding

private const val imagesBaseUrl = "https://image.tmdb.org/t/p/w500/"

class MoviesAdapter(private val movies: ArrayList<Movie>) : RecyclerView.Adapter<MoviesAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(movie: Movie) {
            itemBinding.apply {
                tvTitle.text = movie.title
                tvReleaseDate.text = movie.release_date
                Glide.with(ivPoster.context)
                    .load(imagesBaseUrl + movie.poster_path)
                    .into(ivPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun addMovies(movies: List<Movie>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
    }
}
