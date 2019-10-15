package com.galodb.gtest.di.module

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import com.galodb.domain.repository.Repository
import com.galodb.domain.usecase.GetPopularTvShowsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getPopularTvShows(
        repository: Repository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    )
            : GetPopularTvShowsUseCase {
        return GetPopularTvShowsUseCase(repository, threadExecutor, postExecutionThread)
    }

}
