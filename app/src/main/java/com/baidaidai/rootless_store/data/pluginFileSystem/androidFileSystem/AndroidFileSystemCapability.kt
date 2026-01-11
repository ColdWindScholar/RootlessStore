package com.baidaidai.rootless_store.data.pluginFileSystem.androidFileSystem

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import com.baidaidai.rootless_store.domain.pluginManiFest.model.PluginManiFest
import kotlinx.serialization.json.Json
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

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

    fun createOneVoidFile(destination: File,fileName: String): Boolean{
        val result = File(destination,"$fileName.zip").createNewFile()  // 创建了文件，而非单纯路径
        return result
    }

    fun getFileNames(originalFileUri: Uri): String{
        val name = DocumentFile.fromSingleUri(context, originalFileUri)!!.name
        return name!!
    }

    fun copyFile(originFileURI: Uri, destination: File): Boolean {
        val fileName = getFileNames(originFileURI)
        createOneVoidFile(destination,fileName)
        val operationFile = File(destination,fileName)
        return try {
            context.contentResolver.openInputStream(originFileURI).use { input ->
                FileOutputStream(operationFile).use { output ->
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

    private fun confirmPathExists(path: String): Boolean{
        Log.d("confirmPathExists",File(context.getExternalFilesDir(null),path.toString()).exists().toString())
        return File(context.getExternalFilesDir(null),path.toString()).exists()
    }

}
