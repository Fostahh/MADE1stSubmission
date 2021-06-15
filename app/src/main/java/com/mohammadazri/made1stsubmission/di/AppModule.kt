package com.mohammadazri.made1stsubmission.di

import com.mohammadazri.made1stsubmission.core.domain.usecase.MovieInteractor
import com.mohammadazri.made1stsubmission.core.domain.usecase.MovieUseCase
import com.mohammadazri.made1stsubmission.core.domain.usecase.TvShowInteractor
import com.mohammadazri.made1stsubmission.core.domain.usecase.TvShowUseCase
import com.mohammadazri.made1stsubmission.detail.movie.DetailMovieViewModel
import com.mohammadazri.made1stsubmission.detail.tvshow.DetailTvShowViewModel
import com.mohammadazri.made1stsubmission.home.movie.MovieViewModel
import com.mohammadazri.made1stsubmission.home.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get())}
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
}