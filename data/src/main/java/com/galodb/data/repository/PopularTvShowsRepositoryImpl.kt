package com.galodb.data.repository

import com.galodb.data.mapper.TvShowMapper
import com.galodb.data.network.GTestApi
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.repository.PopularTvShowsRepository
import io.reactivex.Single
import javax.inject.Inject

class PopularTvShowsRepositoryImpl @Inject constructor(
    private val netApi: GTestApi,
    private val tvShowMapper: TvShowMapper
) : PopularTvShowsRepository {

    override fun getPopularTvShows(page: Int): Single<List<TvShowModel>> {
        return netApi.getPopularTvShows(page = page).map { response ->
            tvShowMapper.transformToModel(response.results)
        }
    }

    override fun getSeveralPopularTvShows(
        page: Int,
        numberOfPages: Int
    ): Single<List<TvShowModel>> {
        val singleList = mutableListOf<Single<List<TvShowModel>>>()

        for (i in page until (page + numberOfPages)) {
            singleList.add(getPopularTvShows(i))
        }

        return Single.zip(singleList) { list ->
            list
                .mapNotNull { it as? List<*> }
                .flatten()
                .mapNotNull { it as? TvShowModel }
        }
    }
}
