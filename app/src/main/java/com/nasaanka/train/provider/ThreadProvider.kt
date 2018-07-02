package com.nasaanka.train.provider

import com.nasaanka.train.domain.common.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

/**
 * MainThread (UI Thread) implementation based on a [Scheduler]
 * which will execute actions on the Android UI thread
 * Created by Exequiel Egbert V. Ponce on 3/4/2018.
 */
@Singleton
class ThreadProvider : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
