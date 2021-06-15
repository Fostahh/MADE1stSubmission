package com.mohammadazri.made1stsubmission.detail.tvshow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mohammadazri.made1stsubmission.R
import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow
import com.mohammadazri.made1stsubmission.databinding.ActivityDetailTvShowBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailTvShowActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailTvShowBinding
    private var favoriteState = false
    private lateinit var tvShowDomain: TvShow
    private val detailTvShowViewModel: DetailTvShowViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvShowId = intent.getIntExtra(EXTRA_TVSHOW, 0)

        tvShowId.let { id ->
            detailTvShowViewModel.getTvShowById(id).observe(this, { detailTvShow ->
                detailTvShow?.let {
                    when(detailTvShow) {
                        is Resource.Loading -> {
                            binding.progressBar2.visibility = View.VISIBLE
                            binding.linearLayoutParentTvShow.visibility = View.GONE
                        }
                        is Resource.Success  -> {
                            binding.progressBar2.visibility = View.GONE
                            binding.linearLayoutParentTvShow.visibility = View.VISIBLE
                            populateTvShow(it)
                        }
                        is Resource.Error  -> {
                            binding.progressBar2.visibility = View.GONE
                            Toast.makeText(this, "There is an error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }

        binding.imageViewFavoriteTvShow.setOnClickListener(this)
    }

    private fun populateTvShow(tvShow: Resource<TvShow>) {
        tvShow.data?.let {
            it.image?.let { imageUrl ->
                Glide.with(applicationContext)
                    .load("https://image.tmdb.org/t/p/w500${imageUrl}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(binding.imageViewDetailTvShowImage)
            }
            binding.textViewDetailTvShowTitleValue.text = it.title
            binding.textViewDetailTvShowYearValue.text = it.releaseDate
            binding.textViewDetailTvShowGenreValue.text = it.genres
            val seasonepisodeValue = resources.getString(R.string.season_episode_value, it.seasons, it.episodes)
            binding.textViewDetailTvShowEpisodeSeasonValue.text = seasonepisodeValue
            binding.textViewDetailTvShowStarValue.text = it.rating.toString()
            binding.textViewDetailTvShowSynopsisValue.text = it.overview

            it.isFavorite?.let { state ->
                if(state) {
                    favoriteState = state
                    setFavoriteIcon(favoriteState)
                } else {
                    favoriteState = state
                    setFavoriteIcon(favoriteState)
                }
            }

            tvShowDomain = it
        }
    }

    private fun setFavoriteIcon(state: Boolean) {
        if (state) {
            binding.imageViewFavoriteTvShow.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.imageViewFavoriteTvShow.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.imageViewFavoriteTvShow) {
            if (favoriteState) {
                Toast.makeText(
                    this@DetailTvShowActivity,
                    "Tv Show removed from favorite",
                    Toast.LENGTH_SHORT
                ).show()
                favoriteState = false
                setFavoriteIcon(favoriteState)
                detailTvShowViewModel.setFavoriteTvShow(tvShowDomain, favoriteState)
            } else {
                Toast.makeText(
                    this@DetailTvShowActivity,
                    "Tv Show added to favorite",
                    Toast.LENGTH_SHORT
                ).show()
                favoriteState = true
                setFavoriteIcon(favoriteState)
                detailTvShowViewModel.setFavoriteTvShow(tvShowDomain, favoriteState)
            }
        }
    }

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }
}