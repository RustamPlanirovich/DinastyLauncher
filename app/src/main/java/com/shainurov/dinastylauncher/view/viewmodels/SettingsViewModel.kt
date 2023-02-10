package com.shainurov.dinastylauncher.view.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shainurov.dinastylauncher.R
import com.shainurov.dinastylauncher.di.ResourcesProviderModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val resourcesProvider: ResourcesProviderModule,
) : ViewModel() {

    private val _stoikSwitch: MutableLiveData<Boolean> = MutableLiveData()
    var stoikSwith: LiveData<Boolean> = _stoikSwitch

    init {
        _stoikSwitch.value =
            sharedPreferences.getBoolean(resourcesProvider.getString(R.string.stoik_history), false)
    }

    fun saveStoikSwtichChecked(isChecked: Boolean) {
        sharedPreferences.edit()
            .putBoolean(resourcesProvider.getString(R.string.stoik_history), isChecked).apply()
    }
}