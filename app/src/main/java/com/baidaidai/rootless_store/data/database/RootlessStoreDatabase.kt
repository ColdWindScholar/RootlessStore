package com.baidaidai.rootless_store.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoDAO
import com.baidaidai.rootless_store.data.plugin.room.PluginInfoEntity
import com.baidaidai.rootless_store.data.source.database.PluginSourceDAO
import com.baidaidai.rootless_store.data.source.database.PluginSourceEntity

@Database(
    entities = [
        PluginInfoEntity::class,
        PluginSourceEntity::class
        // 其它表也一起加进来
    ],
    version = 1,
    exportSchema = true
)
abstract class RootlessStoreDatabase : RoomDatabase() {
    abstract fun pluginInfoDao(): PluginInfoDAO
    abstract fun pluginSourceDao(): PluginSourceDAO
    // 其它 DAO 也在这里暴露
}


//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        @ApplicationContext context: Context
//    ): AppDatabase =
//        Room.databaseBuilder(context, AppDatabase::class.java, "rootless_store.db")
//            .build()
//
//    @Provides
//    fun providePluginDao(db: AppDatabase): PluginInfoDAO = db.pluginDao()
//}
//
//@Composable
//fun e(){
//    val k = Room.databaseBuilder(
//        context = RootLessStoreLocalContext.current,
//        klass = AppDatabase::class.java,
//        name = "pluginInfoDataBase"
//    ).build()
//}