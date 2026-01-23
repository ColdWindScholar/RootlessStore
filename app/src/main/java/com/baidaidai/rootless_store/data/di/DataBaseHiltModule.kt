package com.baidaidai.rootless_store.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baidaidai.rootless_store.data.local.room.PluginInfoDAO
import com.baidaidai.rootless_store.data.local.room.PluginInfoDataBase
//import com.baidaidai.rootless_store.data.pluginInfoRepository.AppDatabase_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseHiltModule {

    @Provides
    @Singleton
    fun providePluginInfoDataBase(
        @ApplicationContext
        context: Context
    ): PluginInfoDataBase{
        return Room.databaseBuilder(
            context = context,
            klass = PluginInfoDataBase::class.java,
            name = "PluginInfoDataBase"
        ).build()
    }

    @Provides
    fun providePluginInfoDAO(
        pluginInfoDataBase: PluginInfoDataBase
    ): PluginInfoDAO {
        return pluginInfoDataBase.pluginInfoDao()
    }


}