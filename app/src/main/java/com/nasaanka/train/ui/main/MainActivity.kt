package com.nasaanka.train.ui.main

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.nasaanka.train.R
import com.nasaanka.train.domain.model.Train
import com.nasaanka.train.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private var fusedLocationClient: FusedLocationProviderClient? = null

    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    @Inject
    lateinit var mPresenter: MainPresenter

    private var hasPermission: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent()?.inject(this)
        mPresenter.attachView(this)

        hasPermission = hasPermission()
        init()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()

        mPresenter.detachView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var index = 0
        val PermissionsMap = HashMap<String, Int>()
        for (permission in permissions) {
            PermissionsMap[permission] = grantResults[index]
            index++
        }

        hasPermission = PermissionsMap.get(android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            recreate()
        }
    }

    private fun init() {
        /* init location updates */
        locationRequest = LocationRequest().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    mPresenter.setMyLocation(latitude = location.latitude, longitude = location.longitude)
                }
            }
        }

        /* init my location */
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        startLocationUpdates()
    }

    private fun initViews() {
        btn_running.setOnClickListener { onClickRunning() }
        btn_broken.setOnClickListener { onClickBroken() }
    }

    private fun onClickBroken() {
        mPresenter.setStatus(Train.BROKEN)
    }

    private fun onClickRunning() {
        mPresenter.setStatus(Train.RUNNING)
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (hasPermission) {
            fusedLocationClient?.requestLocationUpdates(locationRequest,
                    locationCallback,
                    null)
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient?.removeLocationUpdates(locationCallback)
    }

    private fun hasPermission(): Boolean {
        val hasPermission: Boolean = ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }

        return hasPermission
    }
}
