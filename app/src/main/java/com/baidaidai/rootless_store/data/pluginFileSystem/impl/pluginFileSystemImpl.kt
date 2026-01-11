package com.baidaidai.rootless_store.data.pluginFileSystem.impl

import android.content.Context
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.baidaidai.rootless_store.data.pluginFileSystem.androidFileSystem.AndroidFileSystemCapability
import com.baidaidai.rootless_store.domain.pluginFileSystem.gateway.PluginFileSystemGateway
import java.io.File

class PluginFileSystemGatewayImpl(val context: Context): PluginFileSystemGateway{
    private val defaultPluginLocation = File(context.getExternalFilesDir(null),"Plugin")
    val androidFileSystemCapability = AndroidFileSystemCapability(context)

    override fun installPlugin(originFileURI: Uri) {
        if (androidFileSystemCapability.confirmPluginPathExists()){
            /* got user's files  */
            val a = Math.random()*100  // 假装是哈希值

            _pre_intallPlugin(originFileURI)
        }else{
            androidFileSystemCapability.createFileDir("Plugin")
            installPlugin(originFileURI)
        }
    }


    override fun uninstallPlugin() {

    }

    private fun _pre_intallPlugin(originFileURI: Uri, destination: File = defaultPluginLocation) {
        androidFileSystemCapability.copyFile(originFileURI,destination)
    }
}