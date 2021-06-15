package com.mohammadazri.made1stsubmission.detail.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mohammadazri.made1stsubmission.R
import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.Movie
import com.mohammadazri.made1stsubmission.databinding.ActivityDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailMovieBinding
    private var favoriteState = false
    private lateinit var movieDomain: Movie
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(EXTRA_MOVIE, 0)
        movieId.let { id ->
            detailMovieViewModel.getMovieById(id).observe(this, { detailMovie ->
                detailMovie?.let {
                    when (detailMovie) {
                        is Resource.Loading -> {
                            binding.progressBar3.visibility = View.VISIBLE
                            binding.linearLayoutParentMovie.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding.progressBar3.visibility = View.GONE
                            binding.linearLayoutParentMovie.visibility = View.VISIBLE
                            populateMovie(it)
                        }
                        is Resource.Error -> {
                            binding.progressBar3.visibility = View.GONE
                            Toast.makeText(this, "There is an error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }

        binding.imageViewFavoriteMovie.setOnClickListener(this)
    }

    private fun populateMovie(movie: Resource<Movie>) {
        movie.data?.let {
            it.image?.let { imageUrl ->
                Glide.with(applicationContext)
                    .load("https://image.tmdb.org/t/p/w500${imageUrl}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(binding.imageViewDetailMovieImage)
            }
            binding.textViewDetailMovieTitleValue.text = it.title
            binding.textViewDetailMovieYearValue.text = it.releaseDate
            binding.textViewDetailMovieGenreValue.text = it.genres
            val durationValue = resources.getString(R.string.duration_minutes, it.runtime)
            binding.textViewDetailMovieDurationValue.text = durationValue
            binding.textViewDetailMovieStarValue.text = it.rating.toString()
            binding.textViewDetailMovieSynopsisValue.text = it.overview
            it.isFavorite?.let { state ->
                if (state) {
                    favoriteState = state
                    setFavoriteIcon(favoriteState)
                } else {
                    favoriteState = state
                    setFavoriteIcon(state)
                }
            }
            movieDomain = it
        }
    }


    private fun setFavoriteIcon(state: Boolean) {
        if (state) {
            binding.imageViewFavoriteMovie.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.imageViewFavoriteMovie.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.imageViewFavoriteMovie) {
            if (favoriteState) {
                Toast.makeText(
                    this@DetailMovieActivity,
                    "Movie removed from favorite",
                    Toast.LENGTH_SHORT
                ).show()
                favoriteState = false
                setFavoriteIcon(favoriteState)
                detailMovieViewModel.setFavoriteMovie(movieDomain, favoriteState)
            } else {
                Toast.makeText(
                    this@DetailMovieActivity,
                    "Movie added to favorite",
                    Toast.LENGTH_SHORT
                ).show()
                favoriteState = true
                setFavoriteIcon(favoriteState)
                detailMovieViewModel.setFavoriteMovie(movieDomain, favoriteState)

            }
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}