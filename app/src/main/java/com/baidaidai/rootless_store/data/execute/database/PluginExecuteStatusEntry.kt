package com.baidaidai.rootless_store.data.execute.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifest
import com.baidaidai.rootless_store.domain.plugin.model.PluginState
import com.baidaidai.rootless_store.domain.status.model.PluginStatus
import javax.inject.Inject

@Entity
data class PluginExecuteStatusEntry @Inject constructor(
    @PrimaryKey
    val pluginID: String,
    val executeStatus: PluginState,
    val executePID: Int
){
    companion object{
        fun fromPluginManifest(pluginManifest: PluginManifest,executePID: Int): PluginExecuteStatusEntry{
            return PluginExecuteStatusEntry(
                pluginID = pluginManifest.pluginID,
                executeStatus = PluginState.Great,
                executePID = executePID
            )
        }
    }
}