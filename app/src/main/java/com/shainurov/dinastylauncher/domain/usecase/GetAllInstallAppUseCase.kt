package com.shainurov.dinastylauncher.domain.usecase

import com.shainurov.dinastylauncher.domain.models.AppModel
import com.shainurov.dinastylauncher.domain.repository.AllApplicationRepository
import com.shainurov.dinastylauncher.utils.AppListResponce
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllInstallAppUseCase @Inject constructor(private val allApplicationRepository: AllApplicationRepository) {
    suspend fun execute(): Flow<AppListResponce<List<AppModel>>> {
        return allApplicationRepository.getAllApplicationList()
    }
}