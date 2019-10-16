package com.galodb.gtest.di.module

import com.galodb.data.repository.PopularTvShowsRepositoryImpl
import com.galodb.data.repository.SimilarTvShowsRepositoryImpl
import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import com.galodb.domain.repository.PopularTvShowsRepository
import com.galodb.domain.repository.SimilarTvShowsRepository
import com.galodb.gtest.app.UIThread
import com.galodb.gtest.executor.JobExecutor
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providePopularRepository(popularRepository: PopularTvShowsRepositoryImpl): PopularTvShowsRepository

    @Binds
    abstract fun provideSimilarRepository(similarRepository: SimilarTvShowsRepositoryImpl): SimilarTvShowsRepository

    @Binds
    abstract fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread

}
