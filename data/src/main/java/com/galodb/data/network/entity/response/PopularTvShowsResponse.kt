package com.galodb.data.network.entity.response

import com.google.gson.annotations.SerializedName

data class PopularTvShowsResponse(
    val page: Int,
    @SerializedName("total_results") val totalResults: Long,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<TvShow>
)

data class TvShow(
    val id: Long,
    val name: String,
    val overview: String,
    val popularity: Float,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null
)
