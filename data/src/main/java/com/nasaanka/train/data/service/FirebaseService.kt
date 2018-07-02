package com.nasaanka.train.data.service

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
class FirebaseService {

    companion object {
        const val TRAIN_LOCATION_TABLE = "TRAIN_LOCATION_TABLE"
    }

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference()

    fun <T> write(table: String, key: String, obj: T): Completable = Completable.create({ observer ->
        databaseReference.child(table).child(key).setValue(obj)
                .addOnSuccessListener { observer.onComplete() }
                .addOnFailureListener { observer.onError(it) }
    })
}
