package com.shainurov.dinastylauncher.data.repostiroty

import com.shainurov.dinastylauncher.data.remote.FirebaseStoikHistory
import com.shainurov.dinastylauncher.domain.repository.GetStoikInformationRepository
import com.shainurov.dinastylauncher.utils.StoikRespone
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoikInformationRepositrotyImpl @Inject constructor(
    private val firebaseStoikHistory: FirebaseStoikHistory,
) : GetStoikInformationRepository {
    override suspend fun getAllHistory(): Flow<StoikRespone<MutableMap<String, Any>?>> {
        return firebaseStoikHistory.getHistoryPost()
    }
}