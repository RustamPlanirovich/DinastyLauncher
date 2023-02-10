package com.shainurov.dinastylauncher.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.shainurov.dinastylauncher.data.converter.mapToDomain
import com.shainurov.dinastylauncher.data.models.*
import com.shainurov.dinastylauncher.utils.AppListResponce.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class AllApplicationList @Inject constructor(val context: Context) {

    private var installedAppsList: ArrayList<AppModel> = ArrayList()

    fun loadLis() = flow {
        emit(Loading())
        emit(
            Success(
                getInstalledApps().map { it.mapToDomain() }
            )
        )
    }.catch { error ->
        error.message?.let { errorMessage ->
            emit(Failure(errorMessage))
        }
    }


    @SuppressLint("QueryPermissionsNeeded")
    private suspend fun getInstalledApps(): ArrayList<AppModel> {
        installedAppsList.clear()
        val pm = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val packc = pm.queryIntentActivities(intent, 0).groupBy {
            it.loadLabel(pm).first().titlecase()
        }

        for (i in packc) {
            installedAppsList.add(AppModel(i.key.toString(), null, null, 0))

            for (j in i.value) {
                val appName = j.loadLabel(pm).toString()
                val icon = j.loadIcon(pm)
                val packages = j.activityInfo.packageName

                installedAppsList.add(AppModel(appName, icon, packages, 1))
            }
        }

        installedAppsList.sortBy { it.name.capitalized() }
        return installedAppsList
    }

    private fun String.capitalized(): String {
        return this.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }

}