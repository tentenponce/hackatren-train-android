package com.nasaanka.train.ui.base

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/22/2018.
 */
/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView benefitId that wants to be attached with.
 */
interface Presenter<in V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}
