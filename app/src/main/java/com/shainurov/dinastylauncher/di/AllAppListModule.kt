package com.shainurov.dinastylauncher.di

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.shainurov.dinastylauncher.data.local.AllApplicationList
import com.shainurov.dinastylauncher.data.remote.FirebaseStoikHistory
import com.shainurov.dinastylauncher.data.repostiroty.AllApplicationRepositroyImpl
import com.shainurov.dinastylauncher.data.repostiroty.GetStoikInformationRepositrotyImpl
import com.shainurov.dinastylauncher.domain.repository.AllApplicationRepository
import com.shainurov.dinastylauncher.domain.repository.GetStoikInformationRepository
import com.shainurov.dinastylauncher.domain.usecase.GetAllInstallAppUseCase
import com.shainurov.dinastylauncher.domain.usecase.GetStoikHistoryUseCase
import com.shainurov.dinastylauncher.utils.StoikRespone
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AllAppListModule {

    @Provides
    @Singleton
    fun allApplicationList(@ApplicationContext appContext: Context) = AllApplicationList(appContext)

    @Provides
    fun allApplicationRepository(@ApplicationContext appContext: Context): AllApplicationRepository {
        return AllApplicationRepositroyImpl(allApplicationList(appContext))
    }

    @Provides
    fun firebaseStoikHistory() =
        FirebaseStoikHistory(provideFirebaseInstance())

    @Provides
    fun allStoikHistoryRepository(): GetStoikInformationRepository {
        return GetStoikInformationRepositrotyImpl(firebaseStoikHistory())
    }

    @Provides
    fun getAllInstallAppUseCase(@ApplicationContext appContext: Context): GetAllInstallAppUseCase {
        return GetAllInstallAppUseCase(allApplicationRepository(appContext))
    }

    @Provides
    fun getStoikHistoryUseCase(
    ): GetStoikHistoryUseCase {
        return GetStoikHistoryUseCase(allStoikHistoryRepository())
    }

    @Provides
    @Singleton
    fun provideFirebaseInstance(): Firebase = Firebase

}

