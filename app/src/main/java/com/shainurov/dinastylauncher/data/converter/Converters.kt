package com.shainurov.dinastylauncher.data.converter


import com.shainurov.dinastylauncher.domain.models.AppModel
import com.shainurov.dinastylauncher.data.models.AppModel as Model


fun Model.mapToDomain() = AppModel(
    name = name, icon = icon, packages = packages, type = type,
)


