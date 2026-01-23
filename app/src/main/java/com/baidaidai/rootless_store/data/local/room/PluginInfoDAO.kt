package com.baidaidai.rootless_store.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginManiFest
import javax.inject.Inject

@Dao
interface PluginInfoDAO {
    /**
     * CURD
     */

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOnePluginInfo(pluginInfoEntity: PluginInfoEntity)


    // Read
    @Query("SELECT * FROM pluginInfo WHERE pluginID = :pluginID LIMIT 1")
    suspend fun getOneEntirePluginInfoByPluginID(pluginID: String): PluginManiFest?

    @Query(value = "SELECT * FROM pluginInfo")
    suspend fun getEntirePluginManifest(): List<PluginManiFest>?

    // Delete
    @Delete
    suspend fun deleteOneEntirePluginInfo(pluginInfoEntity: PluginInfoEntity)

    /**
     * Other methods, such as update、disable、configuration change,
     * Will add in no-longer future
     */
}