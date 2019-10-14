package com.galodb.gtest.view.base

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun <T> Single<T>.subscribeDisposable(
        onSuccess: (T) -> Unit = {},
        onError: (throwable: Throwable) -> Unit = {}
    ): Disposable {
        val disposable = this.subscribe({ onSuccess(it) }, { onError(it) })
        compositeDisposable.add(disposable)
        return disposable
    }

    fun <T> Flowable<T>.subscribeDisposable(
        onNext: (T) -> Unit = {},
        onError: (throwable: Throwable) -> Unit = {},
        onComplete: () -> Unit = {}
    ): Disposable {
        val disposable = this.subscribe({ onNext(it) }, { onError(it) }, { onComplete() })
        compositeDisposable.add(disposable)
        return disposable
    }

    fun Completable.subscribeDisposable(
        onComplete: () -> Unit = {},
        onError: (throwable: Throwable) -> Unit = {}
    ): Disposable {
        val disposable = this.subscribe({ onComplete() }, { onError(it) })
        compositeDisposable.add(disposable)
        return disposable
    }

}
