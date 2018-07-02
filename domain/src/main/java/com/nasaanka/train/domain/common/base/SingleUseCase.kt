package com.nasaanka.train.domain.common.base

import com.nasaanka.train.domain.common.executor.PostExecutionThread
import com.nasaanka.train.domain.common.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers

/*
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
/**
 * Handles the input port <Param></Param> and the output port <Return>
 *
 * @param return
 * @param parameter
 */
abstract class SingleUseCase<Return, Param>(@param:NonNull private val threadExecutor: ThreadExecutor,
                                            @param:NonNull private val postExecutionThread: PostExecutionThread) {

    abstract fun buildObservable(@NonNull param: Param): Single<Return>

    fun execute(@NonNull param: Param): Single<Return> {
        return buildObservable(param)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
    }
}
