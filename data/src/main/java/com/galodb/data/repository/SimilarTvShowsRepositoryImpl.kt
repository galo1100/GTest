package com.galodb.data.repository

import com.galodb.data.mapper.TvShowMapper
import com.galodb.data.network.GTestApi
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.repository.SimilarTvShowsRepository
import io.reactivex.Single
import javax.inject.Inject

class SimilarTvShowsRepositoryImpl @Inject constructor(
    private val netApi: GTestApi,
    private val tvShowMapper: TvShowMapper
) : SimilarTvShowsRepository {

    override fun getSimilarTvShows(tvShowId: Long): Single<List<TvShowModel>> {
        return netApi.getSimilarTvShows(tvShowId = tvShowId).map { response ->
            tvShowMapper.transformToModel(response.results)
        }
    }
}
