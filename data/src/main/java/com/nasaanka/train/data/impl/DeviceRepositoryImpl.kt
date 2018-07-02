package com.nasaanka.train.data.impl

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.nasaanka.train.domain.repository.DeviceRepository
import javax.inject.Inject

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
class DeviceRepositoryImpl @Inject constructor(
        private val context: Context
) : DeviceRepository {

    @SuppressLint("HardwareIds")
    override fun getDeviceId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}