package com.shainurov.dinastylauncher.domain.usecase

import com.shainurov.dinastylauncher.domain.repository.GetStoikInformationRepository
import com.shainurov.dinastylauncher.utils.StoikRespone
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoikHistoryUseCase @Inject constructor(private val allStoikHistoryRepository: GetStoikInformationRepository) {
    suspend fun execute(): Flow<StoikRespone<MutableMap<String, Any>?>> {
        return allStoikHistoryRepository.getAllHistory()
    }

}