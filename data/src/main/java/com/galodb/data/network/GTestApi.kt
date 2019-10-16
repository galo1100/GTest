package com.galodb.data.network

import com.galodb.data.BuildConfig
import com.galodb.data.network.entity.response.TvShowsListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GTestApi {

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_TOKEN,
        @Query("page") page: Int
    ): Single<TvShowsListResponse>

    @GET("tv/{tv_show_id}/similar")
    fun getSimilarTvShows(
        @Path("tv_show_id") tvShowId: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_TOKEN
    ): Single<TvShowsListResponse>
}
