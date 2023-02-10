package com.shainurov.dinastylauncher.domain.repository

import com.shainurov.dinastylauncher.utils.StoikRespone
import kotlinx.coroutines.flow.Flow

interface GetStoikInformationRepository {
    suspend fun getAllHistory(): Flow<StoikRespone<MutableMap<String, Any>?>>
}