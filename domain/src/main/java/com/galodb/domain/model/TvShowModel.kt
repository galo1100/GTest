package com.galodb.domain.model

data class TvShowModel(
    val id: Long,
    val name: String,
    val overview: String,
    val popularity: Float,
    val originalName: String,
    val genreIds: List<Int>,
    val voteCount: Int,
    val voteAverage: Float,
    val originCountry: List<String>,
    val originalLanguage: String,
    val firstAirDate: String,
    val backdropPath: String? = null,
    val posterPath: String? = null
)
