package com.shainurov.dinastylauncher

import android.app.Application
import android.content.pm.ApplicationInfo
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.material.color.DynamicColors
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

    }


}