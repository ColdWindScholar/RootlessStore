package com.baidaidai.rootless_store.data.pluginInfoRepository

import androidx.compose.runtime.Composable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baidaidai.rootless_store.RootLessStoreLocalContext

@Database(
    entities = [
        PluginInfoEntity::class,
        // 其它表也一起加进来
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pluginInfoDao(): PluginInfoDAO
    // 其它 DAO 也在这里暴露
}

@Composable
fun e(){
    val k = Room.databaseBuilder(
        context = RootLessStoreLocalContext.current,
        klass = AppDatabase::class.java,
        name = "index.db"
    ).build()


}