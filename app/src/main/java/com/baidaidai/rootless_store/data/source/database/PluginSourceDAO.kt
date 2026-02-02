package com.baidaidai.rootless_store.data.source.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PluginSourceDAO {
    /**
     * CURD
     */

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOnePluginSource(pluginSourceEntity: PluginSourceEntity)

    // Update
    @Query(
        "UPDATE pluginSource SET sourceName = :sourceName, sourceURI = :sourceURI " +
            "WHERE sourceID = :sourceID"
    )
    suspend fun updateOnePluginSource(sourceID: String, sourceName: String, sourceURI: String)

    // Read
    @Query("SELECT * FROM pluginSource WHERE sourceID = :sourceID LIMIT 1")
    suspend fun getOnePluginSourceBySourceID(sourceID: String): PluginSourceEntity?

    @Query(value = "SELECT * FROM pluginSource")
    suspend fun getAllPluginSources(): List<PluginSourceEntity>?

    // Delete
    @Delete
    suspend fun deleteOnePluginSource(pluginSourceEntity: PluginSourceEntity)

    /**
     * Other methods will be added in the future.
     */
}
