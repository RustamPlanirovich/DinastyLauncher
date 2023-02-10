package com.shainurov.dinastylauncher.domain.models

import android.graphics.drawable.Drawable


data class AppModel(
    var name: String,
    var icon: Drawable? = null,
    var packages: String? = null,
    var type: Int,
)