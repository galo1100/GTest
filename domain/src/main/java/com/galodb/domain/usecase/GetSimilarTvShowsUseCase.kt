package com.galodb.domain.usecase

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.repository.SimilarTvShowsRepository
import com.galodb.domain.usecase.base.SingleUseCase
import io.reactivex.Single

class GetSimilarTvShowsUseCase(
    private val repository: SimilarTvShowsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<TvShowModel>, GetSimilarTvShowsUseCase.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseSingle(params: Params): Single<List<TvShowModel>> {
        return repository.getSimilarTvShows(params.tvShowId)
    }

    data class Params(val tvShowId: Long)
}
