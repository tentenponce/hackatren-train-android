package com.nasaanka.train.ui.main

import com.nasaanka.train.domain.interactor.user.SaveTrainLocation
import com.nasaanka.train.domain.model.Train
import com.nasaanka.train.ui.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainPresenter @Inject constructor(val saveTrainLocation: SaveTrainLocation) : BasePresenter<MainMvpView>() {

    private var mLatitude: Double = 0.0
        set(value) { // override set method
            field = value // set the value of this variable
            value.let { latitudeObservable.onNext(it) } // trigger on next
        }

    private var mLongitude: Double = 0.0
        set(value) {
            field = value
            value.let { longitudeObservable.onNext(it) }
        }

    private var mStatus: Int = Train.RUNNING
        set(value) {
            field = value
            value.let { statusObservable.onNext(it) }
        }

    /* observable to be triggered for changes */
    private val latitudeObservable = BehaviorSubject.createDefault(mLatitude)
    private val longitudeObservable = BehaviorSubject.createDefault(mLongitude)
    private val statusObservable = BehaviorSubject.createDefault(mStatus)

    init {
        Observable.zip(
                latitudeObservable,
                longitudeObservable,
                BiFunction { latitude: Double, longitude: Double ->
                    Pair(latitude, longitude)
                })
                .flatMapCompletable({
                    saveTrainLocation.execute(Train(latitude = it.first, longitude = it.second, status = mStatus))
                })
                .doOnSubscribe({ compositeDisposable::add })
                .subscribe()

        statusObservable
                .flatMapCompletable {
                    saveTrainLocation.execute(Train(latitude = mLatitude, longitude = mLongitude, status = it))
                }
                .doOnSubscribe({ compositeDisposable::add })
                .subscribe()

    }

    fun setMyLocation(latitude: Double, longitude: Double) {
        mLatitude = latitude
        mLongitude = longitude
    }

    fun setStatus(status: Int) {
        mStatus = status
    }
}