package com.galodb.gtest.di.module

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import com.galodb.domain.repository.PopularTvShowsRepository
import com.galodb.domain.repository.SimilarTvShowsRepository
import com.galodb.domain.usecase.GetPopularTvShowsUseCase
import com.galodb.domain.usecase.GetSimilarTvShowsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getPopularTvShows(
        repository: PopularTvShowsRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): GetPopularTvShowsUseCase {
        return GetPopularTvShowsUseCase(repository, threadExecutor, postExecutionThread)
    }

    @Provides
    fun getSimilarTvShows(
        repository: SimilarTvShowsRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): GetSimilarTvShowsUseCase {
        return GetSimilarTvShowsUseCase(repository, threadExecutor, postExecutionThread)
    }

}
