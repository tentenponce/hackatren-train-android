package com.nasaanka.train.di.module

import android.content.Context
import com.nasaanka.train.di.AppContext
import com.nasaanka.train.App
import dagger.Module
import dagger.Provides

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/19/2018.
 */
@Module
class AppModule(val app: App) {

    @Provides
    fun provideApp(): App = app

    @Provides
    @AppContext
    fun provideContext(): Context = app
}