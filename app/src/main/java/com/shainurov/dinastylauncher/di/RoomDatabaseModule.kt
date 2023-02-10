//package com.shainurov.dinastylauncher.di
//
//import android.content.Context
//import androidx.room.Room
//import com.shainurov.dinastylauncher.data.room.AppDatabase
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Singleton
//@InstallIn(SingletonComponent::class)
//class RoomDatabaseModule {
//
//    @Provides
//    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            AppDatabase::class.java,
//            "DinastyLauncher"
//        ).build()
//    }
//}