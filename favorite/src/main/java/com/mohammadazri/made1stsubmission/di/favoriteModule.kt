package com.mohammadazri.made1stsubmission.di

import com.mohammadazri.made1stsubmission.favorite.movie.FavoriteMovieViewModel
import com.mohammadazri.made1stsubmission.favorite.tvshow.FavoriteTvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMovieViewModel(get())}
    viewModel { FavoriteTvShowViewModel(get())}
}