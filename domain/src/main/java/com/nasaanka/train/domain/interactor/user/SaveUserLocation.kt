package com.nasaanka.train.domain.interactor.user

import com.nasaanka.train.domain.common.base.CompletableUseCase
import com.nasaanka.train.domain.common.executor.PostExecutionThread
import com.nasaanka.train.domain.common.executor.ThreadExecutor
import com.nasaanka.train.domain.model.User
import com.nasaanka.train.domain.repository.DeviceRepository
import com.nasaanka.train.domain.repository.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Gets the device ID and make it as an ID for the user
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
class SaveUserLocation @Inject constructor(threadExecutor: ThreadExecutor,
                                           postExecutionThread: PostExecutionThread,
                                           private val userRepository: UserRepository,
                                           private val deviceRepository: DeviceRepository) :
        CompletableUseCase<User>(threadExecutor, postExecutionThread) {

    override fun buildObservable(param: User): Completable {
        return userRepository.saveUserLocation(param.copy(id = deviceRepository.getDeviceId())) // insert the device ID
    }
}