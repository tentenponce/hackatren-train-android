package com.nasaanka.train.data.impl

import com.nasaanka.train.data.service.FirebaseService
import com.nasaanka.train.domain.model.Train
import com.nasaanka.train.domain.repository.TrainRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
class TrainRepositoryImpl @Inject constructor(val firebaseService: FirebaseService) : TrainRepository {

    override fun saveTrainLocation(train: Train): Completable =
            firebaseService.write(FirebaseService.TRAIN_LOCATION_TABLE, "TRAIN_1", train.copy(name = "Train 001"))
}