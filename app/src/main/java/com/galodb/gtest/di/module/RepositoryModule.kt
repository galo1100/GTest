package com.galodb.gtest.di.module

import com.galodb.data.network.repository.RepositoryImpl
import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import com.galodb.domain.repository.Repository
import com.galodb.gtest.app.UIThread
import com.galodb.gtest.executor.JobExecutor
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: RepositoryImpl): Repository

    @Binds
    abstract fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread

}
