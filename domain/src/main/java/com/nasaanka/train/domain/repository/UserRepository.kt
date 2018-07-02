package com.nasaanka.train.domain.repository

import com.nasaanka.train.domain.model.User
import io.reactivex.Completable

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
interface UserRepository {

    fun saveUserLocation(user: User): Completable
}