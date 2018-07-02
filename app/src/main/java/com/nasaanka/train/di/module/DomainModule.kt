package com.nasaanka.train.di.module

import android.content.Context
import com.nasaanka.train.data.common.executor.JobExecutor
import com.nasaanka.train.data.impl.DeviceRepositoryImpl
import com.nasaanka.train.data.impl.UserRepositoryImpl
import com.nasaanka.train.data.service.FirebaseService
import com.nasaanka.train.di.AppContext
import com.nasaanka.train.domain.common.executor.PostExecutionThread
import com.nasaanka.train.domain.common.executor.ThreadExecutor
import com.nasaanka.train.domain.repository.DeviceRepository
import com.nasaanka.train.domain.repository.UserRepository
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
    internal fun userRepository(service: FirebaseService): UserRepository {
        return UserRepositoryImpl(service)
    }

    @Provides
    @Singleton
    internal fun deviceRepository(@AppContext context: Context): DeviceRepository {
        return DeviceRepositoryImpl(context)
    }
}