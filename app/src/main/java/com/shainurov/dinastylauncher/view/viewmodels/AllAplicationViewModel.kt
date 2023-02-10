package com.shainurov.dinastylauncher.view.viewmodels

import androidx.lifecycle.*
import com.shainurov.dinastylauncher.domain.models.AppModel
import com.shainurov.dinastylauncher.domain.usecase.GetAllInstallAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAplicationViewModel @Inject constructor(
    private val getAllInstallAppUseCase: GetAllInstallAppUseCase,
) : ViewModel() {

    fun getApplicationList() = liveData(Dispatchers.IO) {
        getAllInstallAppUseCase.execute().collect(){response->
            emit(response)
        }
    }

}