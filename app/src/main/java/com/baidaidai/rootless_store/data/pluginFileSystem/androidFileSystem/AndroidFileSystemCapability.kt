package com.baidaidai.rootless_store.data.pluginFileSystem.androidFileSystem

import android.content.Context
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class AndroidFileSystemCapability(
    val context: Context
){
    fun confirmPluginPathExists(): Boolean{
        return confirmPathExists("Plugin")
    }

    fun createFileDir(path: String){
        if (!confirmPathExists(path)){
            File(context.getExternalFilesDir(null),path).mkdirs()
        }
    }

    fun createOneVoidFile(destination: File): Boolean{
        Log.d("createOneFile",destination.createNewFile().toString())
        return destination.createNewFile()
    }

    fun copyFile(originFileURI: Uri, destination: File): Boolean {
        createOneVoidFile(destination)
        return try {
            context.contentResolver.openInputStream(originFileURI).use { input ->
                FileOutputStream(destination).use { output ->
                    Log.d("copyFile","e")
                    input!!.copyTo(output)
                }
            }
            Log.d("copyFile","Finally")
            true
        } catch (e: Throwable) {
            e.printStackTrace() // 打印异常日志便于排查
            Log.d("copyFile","Fault")
            false
        }
    }

//    fun useIntentOpenAndroidSAFAndReturnUri(): Uri{
//        val originalIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//    }

    private fun confirmPathExists(path: String): Boolean{
        Log.d("confirmPathExists",File(context.getExternalFilesDir(null),path.toString()).exists().toString())
        return File(context.getExternalFilesDir(null),path.toString()).exists()
    }

}
