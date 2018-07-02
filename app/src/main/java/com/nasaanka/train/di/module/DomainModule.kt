package com.nasaanka.train.di.module

import com.nasaanka.train.data.common.executor.JobExecutor
import com.nasaanka.train.data.impl.TrainRepositoryImpl
import com.nasaanka.train.data.service.FirebaseService
import com.nasaanka.train.domain.common.executor.PostExecutionThread
import com.nasaanka.train.domain.common.executor.ThreadExecutor
import com.nasaanka.train.domain.repository.TrainRepository
import com.nasaanka.train.provider.ThreadProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */

@Module
class DomainModule {

    /* Executor */
    @Provides
    @Singleton
    internal fun providesThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    @Provides
    @Singleton
    internal fun postExecutionThread(): PostExecutionThread {
        return ThreadProvider()
    }

    /* Repository */
    @Provides
    @Singleton
    internal fun trainRepository(service: FirebaseService): TrainRepository {
        return TrainRepositoryImpl(service)
    }
}