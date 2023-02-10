package com.shainurov.dinastylauncher.view.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shainurov.dinastylauncher.R
import com.shainurov.dinastylauncher.di.ResourcesProviderModule
import com.shainurov.dinastylauncher.domain.usecase.GetStoikHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    sharedPreferences: SharedPreferences,
    resourcesProvider: ResourcesProviderModule,
    private val getStoikHistoryUseCase: GetStoikHistoryUseCase,
) : ViewModel() {

    private val _stoikSwitch: MutableLiveData<Boolean> = MutableLiveData()
    var stoikSwith: LiveData<Boolean> = _stoikSwitch

    init {
        _stoikSwitch.value =
            sharedPreferences.getBoolean(resourcesProvider.getString(R.string.stoik_history), false)
    }

    fun getHistoryPosts() = liveData(Dispatchers.IO) {
        getStoikHistoryUseCase.execute().collect(){ response ->
            emit(response)
        }
    }
}