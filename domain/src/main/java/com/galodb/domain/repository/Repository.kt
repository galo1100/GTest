package com.galodb.domain.repository

import com.galodb.domain.model.TvShowModel
import io.reactivex.Single

interface Repository {

    fun getPopularTvShows(page: Int): Single<List<TvShowModel>>

    fun getSeveralPopularTvShows(page: Int, numberOfPages: Int): Single<List<TvShowModel>>

}
