package com.nasaanka.train.di.component

import android.content.Context
import com.nasaanka.train.App
import com.nasaanka.train.di.AppContext
import com.nasaanka.train.di.module.AppModule
import com.nasaanka.train.di.module.DataModule
import com.nasaanka.train.di.module.DomainModule
import com.nasaanka.train.domain.common.executor.PostExecutionThread
import com.nasaanka.train.domain.common.executor.ThreadExecutor
import com.nasaanka.train.domain.repository.TrainRepository
import dagger.Component
import javax.inject.Singleton

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/17/2018.
 */

@Singleton
@Component(modules = [
    AppModule::class,
    DataModule::class,
    DomainModule::class])
interface AppComponent {

    @AppContext
    fun context(): Context

    fun app(): App

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun trainRepository(): TrainRepository
}