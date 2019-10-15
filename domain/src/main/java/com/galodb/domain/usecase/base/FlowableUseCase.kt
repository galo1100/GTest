package com.galodb.domain.usecase.base

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

abstract class FlowableUseCase<T, PARAMS> protected constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    /**
     * Builds an [Flowable] which will be used when executing the current [FlowableUseCase].
     */
    protected abstract fun buildUseCaseFlowable(params: PARAMS): Flowable<T>

    /**
     * Executes the current use case.
     */
    fun execute(params: PARAMS): Flowable<T> = this.buildUseCaseFlowable(params)
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.getScheduler())
        .unsubscribeOn(Schedulers.from(threadExecutor))
}
