package com.galodb.domain.usecase.base

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in PARAMS> protected constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    /**
     * Builds an [Completable] which will be used when executing the current [CompletableUseCase].
     */
    protected abstract fun buildUseCaseCompletable(params: PARAMS): Completable

    /**
     * Executes the current use case.
     */
    fun execute(params: PARAMS): Completable = this.buildUseCaseCompletable(params)
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.getScheduler())
        .unsubscribeOn(Schedulers.from(threadExecutor))
}
