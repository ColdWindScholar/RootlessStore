package com.baidaidai.rootless_store.data.plugin.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
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
    @Query("UPDATE pluginInfo SET enabled = :enabled WHERE pluginID = :pluginID")
    suspend fun updateEnabled(pluginID: String, enabled: Boolean)

    // Read
    @Query("SELECT * FROM pluginInfo WHERE pluginID = :pluginID LIMIT 1")
    suspend fun getOneEntirePluginInfoByPluginID(pluginID: String): PluginManifestLocal?

    @Query(value = "SELECT * FROM pluginInfo")
    fun getEntirePluginManifest(): Flow<List<PluginManifestRoom>>

    @Query("SELECT COUNT(*) FROM pluginInfo")
    fun getPluginInfoCount(): Flow<Int>

    // Delete
    @Delete
    suspend fun deleteOneEntirePluginInfo(pluginInfoEntity: PluginInfoEntity)

    /**
     * Other methods, such as update、disable、configuration change,
     * Will add in no-longer future
     */
}