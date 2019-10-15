package com.galodb.domain.usecase

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.repository.Repository
import com.galodb.domain.usecase.base.SingleUseCase
import io.reactivex.Single

class GetPopularTvShosUseCase(
    private val repository: Repository, threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<TvShowModel>, GetPopularTvShosUseCase.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseSingle(params: Params): Single<List<TvShowModel>> {
        return repository.getPopularTvShows(params.page)
    }

    data class Params(val page: Int)
}
