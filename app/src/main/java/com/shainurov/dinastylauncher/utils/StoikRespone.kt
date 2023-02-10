package com.shainurov.dinastylauncher.utils

sealed class StoikRespone<out T> {
    class Loading<out T> : StoikRespone<T>()

    data class Success<out T>(
        val data: T,
    ) : StoikRespone<T>()

    data class Failure<out T>(

        val errorMessage: String,
    ) : StoikRespone<T>()
}
