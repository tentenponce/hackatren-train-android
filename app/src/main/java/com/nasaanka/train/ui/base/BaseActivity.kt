package com.nasaanka.train.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nasaanka.train.App
import com.nasaanka.train.di.component.ActivityComponent
import com.nasaanka.train.di.component.ConfigPersistentComponent
import com.nasaanka.train.di.component.DaggerConfigPersistentComponent
import com.nasaanka.train.di.module.ActivityModule
import java.util.concurrent.atomic.AtomicLong

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/20/2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private const val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
    }

    private var mActivityComponent: ActivityComponent? = null

    @SuppressLint("UseSparseArrays")
    private val sComponentsMap = HashMap<Long, ConfigPersistentComponent>()

    private val NEXT_ID = AtomicLong(0)
    private var mActivityId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityComponent(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, mActivityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            sComponentsMap.remove(mActivityId)
        }
        super.onDestroy()
    }

    fun activityComponent(): ActivityComponent? {
        if (mActivityComponent == null) {
            initActivityComponent(null)
        }

        return mActivityComponent
    }

    private fun initActivityComponent(savedInstanceState: Bundle?) {
        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (!sComponentsMap.containsKey(mActivityId)) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .appComponent(App.get(this).getComponent())
                    .build()
            sComponentsMap.put(mActivityId, configPersistentComponent)
        } else {
            configPersistentComponent = sComponentsMap.get(mActivityId)!!
        }

        mActivityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }
}