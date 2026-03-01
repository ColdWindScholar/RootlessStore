package com.baidaidai.rootless_store.data.fileSystem.gateway

import android.content.Context
import android.net.Uri
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import com.baidaidai.rootless_store.domain.plugin.manifest.PluginManifestLocal
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.jvm.javaio.toInputStream
import kotlinx.serialization.json.Json
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream
import javax.inject.Inject

class AndroidFileSystemCapabilityGatewayImpl @Inject constructor(
    @ApplicationContext val context: Context
){

    // Search FS Operator
    fun confirmPluginPathExists(): Boolean{
        return confirmPathExists("Plugin")
    }
    private fun confirmPathExists(path: String): Boolean{
        Log.d("confirmPathExists",
            File(context.getExternalFilesDir(null), path.toString()).exists().toString())
        return File(context.getExternalFilesDir(null), path.toString()).exists()
    }

    // Create FS Operator
    fun createFileDir(path: String){
        if (!confirmPathExists(path)){
            File(context.getExternalFilesDir(null), path).mkdirs()
        }
    }
    fun createOneVoidFile(destination: File, fileName: String): Boolean{
        val result = File(destination, "$fileName.zip").createNewFile()  // 创建了文件，而非单纯路径
        return result
    }
    fun createVoidFileDirectory(pluginRootDirectory: File, directoryName: String): File {
        return File(pluginRootDirectory, directoryName) // 创建文件夹
    }

    // Deprecated FS Operator
    @Deprecated(
        message = "Recommended to use unZipFromFile method, instead of the copyFile method",
        replaceWith = ReplaceWith("unzipFromFile(originFileURI, pluginRootDirectory, directoryName)")
    )
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
        val operationFile = File(destination, "$fileName.zip")

        // The core of copy operator
        context.contentResolver.openInputStream(originFileURI).use { input ->
            FileOutputStream(operationFile).use { output ->
                input!!.copyTo(output)
            }
        }
    }
    @Deprecated(
        message = "Recommended to use unZipFromURI method, instead of the copyFile method",
        replaceWith = ReplaceWith("unZipFromURI(originFileByteChannel, pluginRootDirectory, directoryName)")
    )
    fun copyFile(originFileByteChannel: ByteReadChannel, destination: File, destinationFileName: String) {

        // Get file's name, always powered by readManiFestJsonContent


        // Provide void file, for copy use
        createOneVoidFile(destination, destinationFileName)  // needs prevent override files
        val operationFile = File(destination, "$destinationFileName.zip")

        // The core of copy operator
        FileOutputStream(operationFile).use { out ->
            originFileByteChannel.toInputStream().use { input ->
                input.copyTo(out)
            }
        }
    }

    // Un-Zip FS Operator
    fun unzipFromFile(originFileURI: Uri, pluginRootDirectory: File, directoryName: String? = null) {

        // Get file's name, always powered by readManiFestJsonContent
        val directoryName = when {

            !directoryName.isNullOrBlank() -> {
                directoryName.trim()

            }  // 只有destinationFilName显式指定，否则不走

            else -> {
                readRawPluginManifest(originFileURI).let { json ->
                    readManifestJsonContent(json).pluginPackageName
                }.trim()
            }

        }

        // Create Void Directory
        val createdFileDirectory = createVoidFileDirectory(pluginRootDirectory, directoryName)

        // Open IO Stream
        context.contentResolver.openInputStream(originFileURI).use{ fis ->
            // Unzip from File Input Stream
            ZipInputStream(BufferedInputStream(fis)).use { zis ->
                var entry = zis.nextEntry
                while (entry != null) {
                    val outFile = File(createdFileDirectory, entry.name)

                    if (entry.isDirectory) {
                        outFile.mkdirs()
                    } else {
                        outFile.parentFile?.mkdirs()
                        FileOutputStream(outFile).use { out ->
                            zis.copyTo(out)
                        }
                    }

                    zis.closeEntry()
                    entry = zis.nextEntry
                }
            }

        }
    }
    fun unZipFromURI(originFileByteChannel: ByteReadChannel, pluginRootDirectory: File, directoryName: String){

        // Provide void file, for copy use
        createVoidFileDirectory(pluginRootDirectory, directoryName)  // needs prevent override files
        val operationFile = File(pluginRootDirectory, directoryName)

        // The core of copy operator
        originFileByteChannel.toInputStream().use { input ->
            ZipInputStream(BufferedInputStream(input)).use { zis ->
                var entry = zis.nextEntry
                while (entry != null) {
                    val outFile = File(operationFile, entry.name)

                    if (entry.isDirectory) {
                        outFile.mkdirs()
                    } else {
                        outFile.parentFile?.mkdirs()
                        FileOutputStream(outFile).use { out ->
                            zis.copyTo(out)
                        }
                    }

                    zis.closeEntry()
                    entry = zis.nextEntry
                }
            }
        }
    }

    // Read FS Operator
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
    fun readManifestJsonContent(jsonContent: String): PluginManifestLocal {
        val json = Json {
            ignoreUnknownKeys = true // JSON 多字段也不炸
            isLenient = true
        }
        val manifest: PluginManifestLocal = json.decodeFromString(PluginManifestLocal.Companion.serializer(),jsonContent)
        return manifest
    }

    // Delete FS Operator
    @Deprecated(
        message = "Recommended to use deleteDirectoryByPackageName method, instead of the deleteOneFile method",
        replaceWith = ReplaceWith("deleteDirectoryByPackageName(pluginPackageName)")
    )
    fun deleteOneFile(pluginPackageName: String): Boolean{
        val base = context.getExternalFilesDir(null)
        val targetFile = File(base, "Plugin/${pluginPackageName}.zip")

        return targetFile.delete()
    }
    fun deleteDirectoryByPackageName(pluginPackageName: String): Boolean {
        val pluginRootDirectory = context.getExternalFilesDir(null)
        val targetFile = File(pluginRootDirectory, "Plugin/${pluginPackageName}")

        return targetFile.deleteRecursively()
    }
}