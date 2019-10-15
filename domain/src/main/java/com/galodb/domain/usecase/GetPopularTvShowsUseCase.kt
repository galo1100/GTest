package com.galodb.domain.usecase

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.repository.Repository
import com.galodb.domain.usecase.base.SingleUseCase
import io.reactivex.Single

class GetPopularTvShowsUseCase(
    private val repository: Repository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<TvShowModel>, GetPopularTvShowsUseCase.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseSingle(params: Params): Single<List<TvShowModel>> {
        return repository.getSeveralPopularTvShows(params.initialPage, params.numberOfPages)
    }

    data class Params(val initialPage: Int, val numberOfPages: Int)
}
