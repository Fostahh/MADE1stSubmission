package com.mohammadazri.made1stsubmission.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohammadazri.made1stsubmission.core.databinding.ItemsTvshowBinding
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow

class FavoriteTvShowRecyclerViewAdapter(private val onItemClickCallback: FavoriteTvShowRecyclerViewAdapterInterface) :
    RecyclerView.Adapter<FavoriteTvShowRecyclerViewAdapter.ViewHolder>() {

    private var listTvShow = ArrayList<TvShow>()

    fun setTvShows(tvShows: List<TvShow>?) {
        if (tvShows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class ViewHolder(private val binding: ItemsTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            with(binding) {
                textViewTvShowTitle.text = tvShow.title
                textViewTvShowReleaseDate.text = tvShow.releaseDate
                textViewTvShowStarNumber.text = tvShow.rating.toString()
                tvShow.image?.let {
                    Glide.with(binding.root.context)
                        .load("https://image.tmdb.org/t/p/w500${tvShow.image}")
                        .into(imageViewTvShowImage)
                }
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(tvShow)
                }
            }
        }
    }

    interface FavoriteTvShowRecyclerViewAdapterInterface {
        fun onItemClicked(tvShow: TvShow)
    }

}