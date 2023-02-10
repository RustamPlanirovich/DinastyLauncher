package com.shainurov.dinastylauncher.data.models

import android.graphics.drawable.Drawable
import java.time.LocalDate

data class AppModel(
    var name: String,
    var icon: Drawable? = null,
    var packages: String? = null,
    var type: Int,
)