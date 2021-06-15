package com.mohammadazri.made1stsubmission.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohammadazri.made1stsubmission.core.databinding.ItemsMovieBinding
import com.mohammadazri.made1stsubmission.core.domain.model.Movie

class MovieRecyclerViewAdapter(private val onItemClickCallback: MovieRecyclerViewAdapterInterface) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private var listMovie = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class ViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                textViewMovieTitle.text = movie.title
                textViewMovieReleaseDate.text = movie.releaseDate
                textViewMovieStarNumber.text = movie.rating.toString()
                movie.image?.let {
                    Glide.with(binding.root.context)
                        .load("https://image.tmdb.org/t/p/w500${movie.image}")
                        .into(imageViewMovieImage)
                }
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(movie)
                }
            }
        }
    }

    interface MovieRecyclerViewAdapterInterface {
        fun onItemClicked(movie: Movie)
    }
}