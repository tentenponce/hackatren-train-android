package com.nasaanka.train.domain.model

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
data class Train(
        val latitude: Double,
        val longitude: Double,
        val status: Int) {
    companion object {
        public const val RUNNING = 1
        public const val BROKEN = 2
    }
}