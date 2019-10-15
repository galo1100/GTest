package com.galodb.data.network.repository

import com.galodb.data.network.GTestApi
import com.galodb.data.network.TvShowMapper
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val netApi: GTestApi
) : Repository {

    override fun getPopularTvShows(page: Int): Single<List<TvShowModel>> {
        return netApi.getPopularTvShows(page = page).map { response ->
            TvShowMapper.transformToModel(response.results)
        }
    }
}
