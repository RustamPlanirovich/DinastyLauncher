package com.shainurov.dinastylauncher.data.repostiroty

import com.shainurov.dinastylauncher.data.local.AllApplicationList
import com.shainurov.dinastylauncher.domain.models.AppModel
import com.shainurov.dinastylauncher.domain.repository.AllApplicationRepository
import com.shainurov.dinastylauncher.utils.AppListResponce
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllApplicationRepositroyImpl @Inject constructor(
    private val allApplicationList: AllApplicationList,
) : AllApplicationRepository {

    override suspend fun getAllApplicationList(): Flow<AppListResponce<List<AppModel>>> {
        return allApplicationList.loadLis()
    }
}