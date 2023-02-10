package com.shainurov.dinastylauncher.data.remote

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.shainurov.dinastylauncher.utils.StoikRespone.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import javax.inject.Inject

class FirebaseStoikHistory @Inject constructor(
    private val provideFirebaseInstance: Firebase,
) {

    private val dateTime: LocalDateTime = LocalDateTime.now()
    private val day = dateTime.dayOfMonth


    fun getHistoryPost() = flow {
        emit(Loading())
        emit(
            Success(
                provideFirebaseInstance.firestore
                    .collection("stoik")
                    .document("$day").get().await().data
            )
        )
    }.catch { error ->
        error.message?.let { errorMessage ->
            emit(Failure(errorMessage))
        }
    }

    fun saveLocalDate(){

    }
}