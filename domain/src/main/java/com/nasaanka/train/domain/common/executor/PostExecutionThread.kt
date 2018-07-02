package com.nasaanka.train.domain.common.executor

import io.reactivex.Scheduler

/**
 * Thread abstraction created to change the execution context from any thread to any other thread.
 * Useful to encapsulate a UI Thread for example, since some job will be done in background, an
 * implementation of this interface will change context and update the UI.
 *
 *
 * Created by Exequiel Egbert V. Ponce on 3/3/2018.
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}
