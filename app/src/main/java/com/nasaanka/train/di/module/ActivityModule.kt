package com.nasaanka.train.di.module

import android.app.Activity
import com.nasaanka.train.di.ActivityContext
import dagger.Module
import dagger.Provides

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/20/2018.
 */
@Module
class ActivityModule(val activity: Activity) {

    @Provides
    fun provideActivity() = activity

    @Provides
    @ActivityContext
    fun provideContext() = activity
}