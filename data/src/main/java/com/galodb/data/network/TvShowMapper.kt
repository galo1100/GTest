package com.galodb.data.network

import com.galodb.data.BuildConfig
import com.galodb.data.network.entity.response.TvShow
import com.galodb.domain.model.TvShowModel

object TvShowMapper {

    fun transformToModel(from: List<TvShow>): List<TvShowModel> {
        return from.map { tvShow -> transformToModel(tvShow) }
    }

    private fun transformToModel(from: TvShow): TvShowModel {
        return TvShowModel(
            from.id,
            from.name,
            from.overview,
            from.popularity,
            from.originalName,
            from.genreIds,
            from.voteCount,
            from.voteAverage,
            from.originCountry,
            from.originalLanguage,
            from.firstAirDate,
            BuildConfig.BASE_IMAGE + from.backdropPath,
            BuildConfig.BASE_IMAGE + from.posterPath
        )
    }
}
