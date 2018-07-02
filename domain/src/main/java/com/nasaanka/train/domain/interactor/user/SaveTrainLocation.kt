package com.nasaanka.train.domain.interactor.user

import com.nasaanka.train.domain.common.base.CompletableUseCase
import com.nasaanka.train.domain.common.executor.PostExecutionThread
import com.nasaanka.train.domain.common.executor.ThreadExecutor
import com.nasaanka.train.domain.model.Train
import com.nasaanka.train.domain.repository.TrainRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Saves the
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
class SaveTrainLocation @Inject constructor(threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread,
                                            private val userRepository: TrainRepository) :
        CompletableUseCase<Train>(threadExecutor, postExecutionThread) {

    override fun buildObservable(param: Train): Completable {
        return userRepository.saveTrainLocation(param)
    }
}