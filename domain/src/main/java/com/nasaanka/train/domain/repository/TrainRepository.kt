package com.nasaanka.train.domain.repository

import com.nasaanka.train.domain.model.Train
import io.reactivex.Completable

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
interface TrainRepository {

    fun saveTrainLocation(train: Train): Completable
}