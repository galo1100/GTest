package com.galodb.domain.usecase.base

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, PARAMS> protected constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    /**
     * Builds an [Single] which will be used when executing the current [SingleUseCase].
     */
    internal abstract fun buildUseCaseSingle(params: PARAMS): Single<T>

    /**
     * Executes the current use case.
     */
    fun execute(params: PARAMS): Single<T> = this.buildUseCaseSingle(params)
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.getScheduler())
        .unsubscribeOn(Schedulers.from(threadExecutor))
}
