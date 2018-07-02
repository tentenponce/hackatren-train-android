package com.nasaanka.train.domain.common.base

import com.nasaanka.train.domain.common.executor.PostExecutionThread
import com.nasaanka.train.domain.common.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
/**
 * abstract class for Completable Use Case (Interactors).
 * Completable only emits success (without any return) or an error.
 *
 *
 * Created by Exequiel Egbert V. Ponce on 3/4/2018.
 */

abstract class CompletableUseCase<Param>(@param:NonNull private val threadExecutor: ThreadExecutor,
                                         @param:NonNull private val postExecutionThread: PostExecutionThread) {

    protected abstract fun buildObservable(@NonNull param: Param): Completable

    fun execute(@NonNull param: Param): Completable {
        return buildObservable(param)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
    }
}
