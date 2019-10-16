package com.galodb.domain.repository

import com.galodb.domain.model.TvShowModel
import io.reactivex.Single

interface SimilarTvShowsRepository {
    fun getSimilarTvShows(tvShowId: Long): Single<List<TvShowModel>>
}
