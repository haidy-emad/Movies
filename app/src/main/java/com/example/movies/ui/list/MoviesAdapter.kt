package com.example.movies.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.data.model.Movie
import com.example.movies.databinding.ItemMovieBinding
import com.example.movies.utils.Constants
import com.example.movies.utils.Constants.imagesBaseUrl

class MoviesAdapter(private val movies: ArrayList<Movie>, private val movieClickListener: IMovieClickListener) : RecyclerView.Adapter<MoviesAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val itemBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(movie: Movie) {
            itemBinding.apply {
                tvTitle.text = movie.title
                tvReleaseDate.text = movie.release_date
                Glide.with(ivPoster.context)
                    .load(imagesBaseUrl + movie.poster_path)
                    .into(ivPoster)

                root.setOnClickListener {
                    movieClickListener.onMovieClicked(movie)
                }
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
