package com.nasaanka.train.data.common.executor

import com.nasaanka.train.domain.common.executor.ThreadExecutor
import io.reactivex.annotations.NonNull

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

import javax.inject.Singleton

/**
 * Decorated [java.util.concurrent.ThreadPoolExecutor]
 * Created by Exequiel Egbert V. Ponce on 3/4/2018.
 */
@Singleton
class JobExecutor : ThreadExecutor {
    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        threadPoolExecutor = ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
                LinkedBlockingQueue<Runnable>(), JobThreadFactory())
    }

    override fun execute(@NonNull runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(@NonNull runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}
