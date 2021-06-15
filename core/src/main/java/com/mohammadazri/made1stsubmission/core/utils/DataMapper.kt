package com.mohammadazri.made1stsubmission.core.utils

import com.mohammadazri.made1stsubmission.core.data.source.local.entity.MovieEntity
import com.mohammadazri.made1stsubmission.core.data.source.local.entity.TvShowEntity
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.DetailMovieResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.movie.MovieResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.DetailTvShowResponse
import com.mohammadazri.made1stsubmission.core.data.source.remote.response.tvshow.TvShowResponse
import com.mohammadazri.made1stsubmission.core.domain.model.Movie
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val listMovie = ArrayList<MovieEntity>()
        input.map {
            val movie =
                MovieEntity(
                    it.id,
                    it.title,
                    it.rating,
                    it.releaseDate,
                    it.overview,
                    it.image,
                    null,
                    null,
                    false
                )
            listMovie.add(movie)
        }
        return listMovie
    }

    fun mapDetailMovieResponseToEntities(input: DetailMovieResponse): MovieEntity {
        val genres = input.genres?.joinToString(", ") { it.name }
        return MovieEntity(
            input.id,
            input.title,
            input.rating,
            input.releaseDate,
            input.overview,
            input.image,
            genres,
            input.runtime,
            false
        )
    }

    fun mapTvShowResponseToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val listTvShow = ArrayList<TvShowEntity>()
        input.map {
            val tvShow =
                TvShowEntity(
                    it.id,
                    it.title,
                    it.releaseDate,
                    it.overview,
                    it.rating,
                    it.image,
                    null,
                    null,
                    null,
                    false
                )
            listTvShow.add(tvShow)
        }
        return listTvShow
    }

    fun mapDetailTvShowResponseToEntities(input: DetailTvShowResponse): TvShowEntity {
        val genres = input.genres?.joinToString(", ") { it.name }
        return TvShowEntity(
            input.id,
            input.title,
            input.releaseDate,
            input.overview,
            input.rating,
            input.image,
            genres,
            input.episodes,
            input.seasons,
            false
        )
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.movieId,
                it.title,
                it.rating,
                it.releaseDate,
                it.overview,
                it.image,
                it.genres,
                it.runtime,
                it.isFavorite
            )
        }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                it.tvShowId,
                it.title,
                it.releaseDate,
                it.overview,
                it.rating,
                it.image,
                it.genres,
                it.episodes,
                it.seasons,
                it.isFavorite
            )
        }

    fun mapDetailMovieEntitiesToDomain(input: MovieEntity) = Movie(
        input.movieId,
        input.title,
        input.rating,
        input.releaseDate,
        input.overview,
        input.image,
        input.genres,
        input.runtime,
        input.isFavorite
    )

    fun mapDetailTvShowEntitiesToDomain(input: TvShowEntity) = TvShow(
        input.tvShowId,
        input.title,
        input.releaseDate,
        input.overview,
        input.rating,
        input.image,
        input.genres,
        input.episodes,
        input.seasons,
        input.isFavorite
    )

    fun mapMovieDomaintoEntity(input: Movie) =
        MovieEntity(
            input.id,
            input.title,
            input.rating,
            input.releaseDate,
            input.overview,
            input.image,
            input.genres,
            input.runtime,
            input.isFavorite
        )

    fun mapTvShowDomaintoEntity(input: TvShow) =
        TvShowEntity(
            input.id,
            input.title,
            input.releaseDate,
            input.overview,
            input.rating,
            input.image,
            input.genres,
            input.episodes,
            input.seasons,
            input.isFavorite
        )
}