package com.baidaidai.rootless_store.data.plugin.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface PluginInfoDAO {
    /**
     * CURD
     */

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOnePluginInfo(pluginInfoEntity: PluginInfoEntity)

    // Update
    @Query("UPDATE pluginInfo SET enabled = :enabled WHERE ID = :pluginID and Type = 0")
    suspend fun updateEnabled(pluginID: String, enabled: Boolean)

    @Query("UPDATE pluginInfo SET enabled = 0 WHERE enabled = 1 and Type = 0")
    suspend fun disableAllPlugin()

    // Read
    @Query("SELECT * FROM pluginInfo WHERE ID = :pluginID and Type = 0 LIMIT 1")
    suspend fun getOneEntirePluginInfoByPluginID(pluginID: String): PluginManifestRoom?

    @Query(value = "SELECT * FROM pluginInfo WHERE Type = 0")
    fun getEntirePluginManifest(): Flow<List<PluginManifestRoom>>

    @Query("SELECT COUNT(*) FROM pluginInfo WHERE Type = 0")
    fun getPluginInfoCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM pluginInfo WHERE Type = 0")
    suspend fun getTotalPluginCount(): Int

    @Query("SELECT COUNT(*) FROM pluginInfo WHERE enabled = 1 and Type = 0")
    suspend fun getEnabledPluginCount(): Int

    // Delete
    @Delete
    suspend fun deleteOnePluginInfo(pluginInfoEntity: PluginInfoEntity)

    /**
     * Other methods, such as update、disable、configuration change,
     * Will add in no-longer future
     */
}
