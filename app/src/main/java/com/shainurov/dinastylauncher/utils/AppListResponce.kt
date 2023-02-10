package com.shainurov.dinastylauncher.utils

sealed class AppListResponce<out T> {
    class Loading<out T> : AppListResponce<T>()

    data class Success<out T>(
        val data: T,
    ) : AppListResponce<T>()

    data class Failure<out T>(

        val errorMessage: String,
    ) : AppListResponce<T>()
}
