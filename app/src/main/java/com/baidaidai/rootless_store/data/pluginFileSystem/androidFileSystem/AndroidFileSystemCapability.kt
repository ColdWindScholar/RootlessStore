package com.baidaidai.rootless_store.data.pluginFileSystem.androidFileSystem

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import com.baidaidai.rootless_store.domain.plugin.model.PluginManifestLocal
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

    /**
     * In Android, Copy File always use iO, Not Official API
     *
     * So, We use "oldFile.InputStream->newFile.OutputStream"
     */
    fun copyFile(originFileURI: Uri, destination: File, destinationFileName: String? = null) {

        // Get file's name, always powered by readManiFestJsonContent
        val fileName = when {
            !destinationFileName.isNullOrBlank() -> destinationFileName.trim()  // 只有destinationFilName显式指定，否则不走
            else -> {
                readRawPluginManifest(originFileURI).let { json ->
                    readManifestJsonContent(json).pluginPackageName
                }.trim()
            }
        }

        // Provide void file, for copy use
        createOneVoidFile(destination,fileName)  // needs prevent override files
        val operationFile = File(destination,"$fileName.zip")

        // The core of copy operator
        context.contentResolver.openInputStream(originFileURI).use { input ->
            FileOutputStream(operationFile).use { output ->
                input!!.copyTo(output)
            }
        }

////        return try {
////            // use的目的是自动关闭流
////            Log.d("copyFile","Finally")
////            true
////        } catch (e: Throwable) {
////            e.printStackTrace() // 打印异常日志便于排查
////            false
////        }
    }

    /**
     *  This is original logic
     *  only return solid json
     */
    fun readRawPluginManifest(uri: Uri): String{
        context.contentResolver.openInputStream(uri).use { inputStream ->
//            if (inputStream == null) {
//                Log.e("readZipContent", "openInputStream returned null, uri=$uri")
//                return
//            }

            ZipInputStream(BufferedInputStream(inputStream)).use { zipInputStream ->
                /**
                 * The entry is like relative path, but root path is zipFile/...
                 */
                var zipEntry = zipInputStream.nextEntry
                while (zipEntry != null) {
                    val entryPath = zipEntry.name                       // 可能是 "a/b/PluginManifest.json"
                    val fileNameOnly = entryPath.substringAfterLast('/') // 取最后一级文件名
                    val isTarget = !zipEntry.isDirectory && fileNameOnly.equals("PluginManifest.json", ignoreCase = true)

                    if (isTarget) {
                        // 读取当前 entry 的内容（这里用 readBytes，适合 manifest 这种小文件）
                        val json = zipInputStream.readBytes().toString(Charsets.UTF_8)
                        Log.d("readZipContent", "PluginManifest.json content: $json")

                        zipInputStream.closeEntry()
                        return json
                    }

                    zipInputStream.closeEntry()
                    zipEntry = zipInputStream.nextEntry
                }

                Log.w("readZipContent", "PluginManifest.json not found, uri=$uri")
                return ""
            }
        }
    }

    fun readManifestJsonContent(jsonContent: String): PluginManifestLocal{
        val json = Json {
            ignoreUnknownKeys = true // JSON 多字段也不炸
            isLenient = true
        }
        val manifest: PluginManifestLocal = json.decodeFromString(PluginManifestLocal.serializer(),jsonContent)
        return manifest
    }

    private fun confirmPathExists(path: String): Boolean{
        Log.d("confirmPathExists",File(context.getExternalFilesDir(null),path.toString()).exists().toString())
        return File(context.getExternalFilesDir(null),path.toString()).exists()
    }

    /**
     * This is private method, only converse json(String) intro PluginMainFest
     *
     * The method is original logic, only return solid pluginPackageName
     */

}
