package com.shainurov.dinastylauncher.domain.repository

import com.shainurov.dinastylauncher.domain.models.AppModel
import com.shainurov.dinastylauncher.utils.AppListResponce
import kotlinx.coroutines.flow.Flow

interface AllApplicationRepository {
    suspend fun getAllApplicationList(): Flow<AppListResponce<List<AppModel>>>
}