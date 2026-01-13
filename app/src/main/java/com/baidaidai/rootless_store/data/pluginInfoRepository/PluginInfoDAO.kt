package com.baidaidai.rootless_store.data.pluginInfoRepository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginManiFest

@Dao
interface PluginInfoDAO {
    @Insert
    fun insertOneEntirePluginInfo(pluginInfoEntity: PluginInfoEntity)

    @Delete
    fun deleteOneEntirePluginInfo(pluginInfoEntity: PluginInfoEntity)

    @Query("SELECT * FROM PLUGININFO WHERE pluginID == :pluginId LIMIT 1")
    fun getOneEntirePluginInfoByPluginID(pluginId: String): PluginManiFest

    /**
     * Other methods, such as update、disable、configuration change,
     * Will add in no-longer future
     */
}