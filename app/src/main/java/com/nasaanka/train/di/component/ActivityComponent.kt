package com.nasaanka.train.di.component

import com.nasaanka.train.di.PerActivity
import com.nasaanka.train.di.module.ActivityModule
import com.nasaanka.train.ui.main.MainActivity
import dagger.Subcomponent

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/20/2018.
 */
@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}