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
interface EnvironmentInfoDAO {
    /**
     * CURD
     */

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneEnvironmentInfo(environmentInfoEntity: PluginInfoEntity)

    // Update
    @Query("UPDATE pluginInfo SET enabled = :enabled WHERE pluginID = :environmentID and pluginType = 1")
    suspend fun updateEnabled(environmentID: String, enabled: Boolean)

    // Read
    @Query("SELECT * FROM pluginInfo WHERE pluginID = :environmentID and pluginType = 1 LIMIT 1")
    suspend fun getOneEntireEnvironmentInfoByEnvironmentID(environmentID: String): PluginManifestLocal?

    @Query(value = "SELECT * FROM pluginInfo WHERE pluginType = 1")
    fun getEntireEnvironmentManifest(): Flow<List<PluginManifestRoom>>

    @Query("SELECT COUNT(*) FROM pluginInfo WHERE pluginType = 1")
    fun getEnvironmentInfoCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM pluginInfo where pluginType = 1")
    suspend fun getTotalEnvironmentCount(): Int

    @Query("SELECT COUNT(*) FROM pluginInfo WHERE enabled = 1 and pluginType = 1")
    suspend fun getEnabledEnvironmentCount(): Int

    @Query("SELECT * FROM pluginInfo WHERE enabled = 1 and pluginType = 1")
    fun getEnabledEnvironment(): Flow<List<PluginManifestRoom>>

    // Delete
    @Delete
    suspend fun deleteOneEnvironmentInfo(environmentInfoEntity: PluginInfoEntity)

    /**
     * Other methods, such as update、disable、configuration change,
     * Will add in no-longer future
     */
}
